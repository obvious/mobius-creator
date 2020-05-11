package `in`.obvious.mobius.creator.codegen

import `in`.obvious.mobius.creator.model.GeneratorConfig
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.KModifier.SEALED
import com.squareup.kotlinpoet.TypeSpec

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
