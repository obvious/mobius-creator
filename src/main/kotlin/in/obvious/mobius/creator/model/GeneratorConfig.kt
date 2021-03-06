package `in`.obvious.mobius.creator.model

data class GeneratorConfig(
  val packageName: String = "",
  val className: String = "",
  val addDependencyEnabled: Boolean = true,
  val mobiusComponents: List<MobiusComponent> = emptyList()
) {

  val isEventGenerated: Boolean
    get() = mobiusComponents.contains(MobiusComponent.Event)

  val isEffectGenerated: Boolean
    get() = mobiusComponents.contains(MobiusComponent.Effect)
}
