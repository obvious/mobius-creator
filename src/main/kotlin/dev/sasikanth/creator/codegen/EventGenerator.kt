package dev.sasikanth.creator.codegen

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.TypeSpec
import dev.sasikanth.creator.model.GeneratorConfig

object EventGenerator : FileGenerator {

  override fun generate(generatorConfig: GeneratorConfig): FileSpec {
    val eventClassName = generatorConfig.className + "Event"

    return FileSpec.builder(generatorConfig.packageName, eventClassName)
      .addType(
        TypeSpec.classBuilder(eventClassName)
          .addModifiers(KModifier.SEALED)
          .build()
      )
      .build()
  }
}
