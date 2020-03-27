package dev.sasikanth.creator.codegen

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.KModifier.SEALED
import com.squareup.kotlinpoet.TypeSpec
import dev.sasikanth.creator.model.GeneratorConfig

object EffectGenerator : FileGenerator {

  override fun generate(generatorConfig: GeneratorConfig): FileSpec {
    val className = generatorConfig.className

    return FileSpec.builder(generatorConfig.packageName, className + "Effect")
        .addType(
            TypeSpec.classBuilder(className + "Effect")
                .addModifiers(SEALED)
                .build()
        )
        .build()
  }
}
