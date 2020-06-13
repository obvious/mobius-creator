package `in`.obvious.mobius.creator

import `in`.obvious.mobius.creator.model.GeneratorConfig
import `in`.obvious.mobius.creator.model.MobiusComponent
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.Messages
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import java.awt.Dimension
import java.awt.Font
import javax.swing.GroupLayout
import javax.swing.JComponent
import javax.swing.JPanel

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
    val dialogPanel = JPanel().apply {
      preferredSize = Dimension(450, 350)
    }
    val layout = GroupLayout(dialogPanel).apply {
      autoCreateGaps = true
      autoCreateContainerGaps = true
      dialogPanel.layout = this
    }

    val packageNameLabel = JBLabel("Package name: ")
    val classNameLabel = JBLabel("Class name: ")
    val componentsLabel = JBLabel("Select components to create").apply {
      font = font.deriveFont(font.style or Font.BOLD)
    }

    layout.setHorizontalGroup(
      createHorizontalGroup(
        layout,
        packageNameLabel,
        classNameLabel,
        componentsLabel
      )
    )

    layout.setVerticalGroup(
      createVerticalGroup(
        layout,
        packageNameLabel,
        classNameLabel,
        componentsLabel
      )
    )

    return dialogPanel
  }

  private fun createVerticalGroup(
    layout: GroupLayout,
    packageNameLabel: JBLabel,
    classNameLabel: JBLabel,
    componentsLabel: JBLabel
  ): GroupLayout.SequentialGroup? {
    return layout.createSequentialGroup()
      .addGroup(
        layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
          .addComponent(packageNameLabel)
          .addComponent(packageNameTextField)
      )
      .addGroup(
        layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
          .addComponent(classNameLabel)
          .addComponent(classNameTextField)
      )
      .addGap(16)
      .addComponent(componentsLabel)
      .addComponent(modelComponentCheckBox)
      .addComponent(eventComponentCheckBox)
      .addComponent(effectComponentCheckBox)
      .addComponent(initComponentCheckBox)
      .addComponent(updateComponentCheckBox)
      .addComponent(effectHandlerComponentCheckBox)
  }

  private fun createHorizontalGroup(
    layout: GroupLayout,
    packageNameLabel: JBLabel,
    classNameLabel: JBLabel,
    componentsLabel: JBLabel
  ): GroupLayout.ParallelGroup? {
    return layout.createParallelGroup()
      .addGroup(
        layout.createSequentialGroup()
          .addGroup(
            layout.createParallelGroup()
              .addComponent(packageNameLabel)
              .addComponent(classNameLabel)
          )
          .addGroup(
            layout.createParallelGroup()
              .addComponent(packageNameTextField)
              .addComponent(classNameTextField)
          )
      )
      .addGap(16)
      .addComponent(componentsLabel)
      .addComponent(modelComponentCheckBox)
      .addComponent(eventComponentCheckBox)
      .addComponent(effectComponentCheckBox)
      .addComponent(initComponentCheckBox)
      .addComponent(updateComponentCheckBox)
      .addComponent(effectHandlerComponentCheckBox)
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

    if (components.contains(MobiusComponent.Update)) {
      if (components.contains(MobiusComponent.Model).not() || components.contains(MobiusComponent.Event).not()) {
        showErrorMessage("Model & Event are necessary to generate Update")
        return
      }
    }

    if (components.contains(MobiusComponent.EffectHandler)) {
      if (components.contains(MobiusComponent.Model).not() ||
        components.contains(MobiusComponent.Event).not() ||
        components.contains(MobiusComponent.Effect).not()
      ) {
        showErrorMessage("Model, Event & Effect are necessary to generate EffectHandler")
        return
      }
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
