package dev.sasikanth.creator.model

data class GeneratorConfig(
  val packageName: String = "",
  val className: String = "",
  val mobiusComponents: List<MobiusComponent> = emptyList()
) {

  val isEventGenerated: Boolean
    get() = mobiusComponents.contains(MobiusComponent.Event)

  val isEffectGenerated: Boolean
    get() = mobiusComponents.contains(MobiusComponent.Effect)
}
