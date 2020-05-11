package `in`.obvious.mobius.creator.model

sealed class MobiusComponent {
  object Model : MobiusComponent()

  object Event : MobiusComponent()

  object Effect : MobiusComponent()

  object Init : MobiusComponent()

  object Update : MobiusComponent()

  object EffectHandler : MobiusComponent()
}
