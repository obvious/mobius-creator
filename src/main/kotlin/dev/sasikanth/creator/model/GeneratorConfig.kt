package dev.sasikanth.creator.model

data class GeneratorConfig(
    val packageName: String = "",
    val className: String = "",
    val mobiusComponents: List<MobiusComponent> = emptyList()
)
