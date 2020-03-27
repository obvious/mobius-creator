package dev.sasikanth.creator

import com.intellij.ide.util.DirectoryChooserUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import com.intellij.psi.impl.file.PsiDirectoryFactory
import com.intellij.util.PlatformIcons

class CreatorAction : AnAction() {

  override fun actionPerformed(event: AnActionEvent) {
    val view = event.getData(LangDataKeys.IDE_VIEW) ?: return
    val project = event.getData(CommonDataKeys.PROJECT) ?: return
    val directory = DirectoryChooserUtil.getOrChooseDirectory(view) ?: return

    val isPackage = PsiDirectoryFactory.getInstance(project).isPackage(directory)
    if (isPackage.not()) return

    val packageRoot = getPackageRoot(project, directory)
    val basePackageName = buildBasePackageName(packageRoot, directory)

    CreatorDialog(basePackageName)
  }

  override fun update(event: AnActionEvent) {
    val presentation = event.presentation.apply {
      icon = PlatformIcons.PACKAGE_ICON
    }

    val project = event.getData(CommonDataKeys.PROJECT)
    val view = event.getData(LangDataKeys.IDE_VIEW)
    if (project == null || view == null) {
      presentation.isEnabledAndVisible = false
      return
    }

    val directories = view.directories
    if (directories.isEmpty()) {
      presentation.isEnabledAndVisible = false
      return
    }

    var isPackage = false
    val factory = PsiDirectoryFactory.getInstance(project)
    for (directory in directories) {
      if (factory.isPackage(directory!!)) {
        isPackage = true
        break
      }
    }

    presentation.isEnabledAndVisible = isPackage
  }

  private fun getPackageRoot(project: Project, selectedDirectory: PsiDirectory): PsiDirectory {
    val directoryFactory = PsiDirectoryFactory.getInstance(project)
    var directory = selectedDirectory
    var parent = directory.parent

    while (parent != null && directoryFactory.isPackage(parent)) {
      directory = parent
      parent = parent.parent
    }

    return directory
  }

  private fun buildBasePackageName(packageRoot: PsiDirectory, directory: PsiDirectory): String {
    if (packageRoot.isEquivalentTo(directory)) {
      return ""
    }
    val root = packageRoot.virtualFile.path
    val current = directory.virtualFile.path
    return current.substring(root.length + 1).replace("/", DELIMITER)
  }

  companion object {
    private const val DELIMITER = "."
  }
}
