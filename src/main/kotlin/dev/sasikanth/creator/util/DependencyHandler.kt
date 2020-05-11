package dev.sasikanth.creator.util

import com.android.ide.common.repository.GradleCoordinate
import com.android.tools.idea.projectsystem.getModuleSystem
import com.intellij.openapi.module.ModuleUtil
import com.intellij.psi.PsiDirectory

class DependencyHandler(directory: PsiDirectory) {

  private val module = ModuleUtil.findModuleForFile(directory.virtualFile, directory.project)

  // TODO (SM): Support adding test gradle dependencies
  fun addDependency(artifact: Artifact) {
    val parsedCoordinate = GradleCoordinate.parseCoordinateString("${artifact.artifactId}:+")
    module?.getModuleSystem()?.registerDependency(parsedCoordinate)
  }
}
