package dev.sasikanth.creator.codegen

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeSpec
import dev.sasikanth.creator.model.GeneratorConfig

object ModelGenerator : FileGenerator {

  override fun generate(generatorConfig: GeneratorConfig): FileSpec {
    val className = generatorConfig.className

    return FileSpec.builder(generatorConfig.packageName, className + "Model")
      .addType(
        TypeSpec.classBuilder(className + "Model")
          .build()
      )
      .build()
  }
}
