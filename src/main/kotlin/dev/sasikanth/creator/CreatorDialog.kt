package dev.sasikanth.creator

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.Messages
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBTextField
import com.intellij.ui.layout.panel
import dev.sasikanth.creator.model.GeneratorConfig
import dev.sasikanth.creator.model.MobiusComponent
import javax.swing.JComponent

class CreatorDialog(basePackageName: String) : DialogWrapper(true) {
  private var _generatorConfig = GeneratorConfig(packageName = basePackageName)
  val generatorConfig: GeneratorConfig
    get() = _generatorConfig

  private val packageNameTextField = JBTextField(generatorConfig.packageName)
  private val classNameTextField = JBTextField()

  private val modelComponentCheckBox = JBCheckBox("Model", true)
  private val eventComponentCheckBox = JBCheckBox("Event", true)
  private val effectComponentCheckBox = JBCheckBox("Effect", true)
  private val initComponentCheckBox = JBCheckBox("Init", false)
  private val updateComponentCheckBox = JBCheckBox("Update", true)
  private val effectHandlerComponentCheckBox = JBCheckBox("Effect Handler", true)

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
      row { eventComponentCheckBox() }
      row { effectComponentCheckBox() }
      row { initComponentCheckBox() }
      row { updateComponentCheckBox() }
      row { effectHandlerComponentCheckBox() }
    }
  }

  override fun doOKAction() {
    val packageName = packageNameTextField.text.orEmpty()
    val className = classNameTextField.text.orEmpty()
    val components = mutableListOf<MobiusComponent>()

    if (modelComponentCheckBox.isSelected) components.add(MobiusComponent.Model)
    if (eventComponentCheckBox.isSelected) components.add(MobiusComponent.Event)
    if (effectComponentCheckBox.isSelected) components.add(MobiusComponent.Effect)
    if (initComponentCheckBox.isSelected) components.add(MobiusComponent.Init)
    if (updateComponentCheckBox.isSelected) components.add(MobiusComponent.Update)
    if (effectHandlerComponentCheckBox.isSelected) components.add(MobiusComponent.EffectHandler)

    if (packageName.isBlank()) {
      showErrorMessage("Package name should not be empty")
      return
    }

    if (className.isBlank()) {
      showErrorMessage("Class name should not be empty")
      return
    }

    if (components.isEmpty()) {
      showErrorMessage("Select at least one component to generate the code")
      return
    }

    _generatorConfig = generatorConfig.copy(
        packageName = packageName,
        className = className,
        mobiusComponents = components
    )

    super.doOKAction()
  }

  private fun showErrorMessage(message: String) {
    Messages.showErrorDialog(message, "Error")
  }
}
