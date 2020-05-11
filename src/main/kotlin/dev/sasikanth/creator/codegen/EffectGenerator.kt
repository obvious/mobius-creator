package dev.sasikanth.creator.codegen

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.KModifier.SEALED
import com.squareup.kotlinpoet.TypeSpec
import dev.sasikanth.creator.model.GeneratorConfig

object EffectGenerator : FileGenerator {

  override fun generate(generatorConfig: GeneratorConfig): FileSpec {
    val effectClassName = generatorConfig.className + "Effect"

    return FileSpec.builder(generatorConfig.packageName, effectClassName)
      .addType(
        TypeSpec.classBuilder(effectClassName)
          .addModifiers(SEALED)
          .build()
      )
      .build()
  }
}
