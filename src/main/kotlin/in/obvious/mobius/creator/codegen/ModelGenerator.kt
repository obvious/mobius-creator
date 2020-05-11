package `in`.obvious.mobius.creator.codegen

import `in`.obvious.mobius.creator.model.GeneratorConfig
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeSpec

object ModelGenerator : FileGenerator {

  override fun generate(generatorConfig: GeneratorConfig): FileSpec {
    val modelClassName = generatorConfig.className + "Model"

    return FileSpec.builder(generatorConfig.packageName, modelClassName)
      .addType(
        TypeSpec.classBuilder(modelClassName)
          .build()
      )
      .build()
  }
}
