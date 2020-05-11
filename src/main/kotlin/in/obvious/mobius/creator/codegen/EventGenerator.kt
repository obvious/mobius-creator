package `in`.obvious.mobius.creator.codegen

import `in`.obvious.mobius.creator.model.GeneratorConfig
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.TypeSpec

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
