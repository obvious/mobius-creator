package dev.sasikanth.creator

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.Messages
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBTextField
import com.intellij.ui.layout.panel
import dev.sasikanth.creator.model.GeneratorConfig
import javax.swing.JComponent

class CreatorDialog(basePackageName: String) : DialogWrapper(true) {
  private var _generatorConfig = GeneratorConfig(packageName = basePackageName)
  val generatorConfig: GeneratorConfig
    get() = _generatorConfig

  private val packageNameTextField = JBTextField(generatorConfig.packageName)
  private val classNameTextField = JBTextField()

  private val modelComponentCheckBox = JBCheckBox("Model", true)

  init {
    title = "Mobius files creator"
    init()
  }

  override fun createCenterPanel(): JComponent? {
    return panel {
      row(label = "Package name: ") { packageNameTextField() }
      row(label = "Class name: ") { classNameTextField() }

      titledRow("Select components to create") {}

      row { modelComponentCheckBox() }
    }
  }

  override fun doOKAction() {
    val packageName = packageNameTextField.text.orEmpty()
    val className = classNameTextField.text.orEmpty()

    if (packageName.isBlank()) {
      showErrorMessage("Package name should not be empty")
      return
    }

    if (className.isBlank()) {
      showErrorMessage("Class name should not be empty")
      return
    }

    _generatorConfig = generatorConfig.copy(
        packageName = packageName,
        className = className
    )

    super.doOKAction()
  }

  private fun showErrorMessage(message: String) {
    Messages.showErrorDialog(message, "Error")
  }
}
