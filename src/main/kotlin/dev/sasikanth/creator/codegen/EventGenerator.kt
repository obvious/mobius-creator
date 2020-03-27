package dev.sasikanth.creator.codegen

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.TypeSpec
import dev.sasikanth.creator.model.GeneratorConfig

object EventGenerator : FileGenerator {

  override fun generate(generatorConfig: GeneratorConfig): FileSpec {
    val className = generatorConfig.className

    return FileSpec.builder(generatorConfig.packageName, className + "Event")
      .addType(
        TypeSpec.classBuilder(className + "Event")
          .addModifiers(KModifier.SEALED)
          .build()
      )
      .build()
  }
}
