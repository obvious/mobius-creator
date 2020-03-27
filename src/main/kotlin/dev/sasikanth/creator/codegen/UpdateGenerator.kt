package dev.sasikanth.creator.codegen

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.TypeSpec
import dev.sasikanth.creator.model.GeneratorConfig
import dev.sasikanth.creator.util.Constants.MOBIUS_PACKAGE_NAME

object UpdateGenerator : FileGenerator {

  override fun generate(generatorConfig: GeneratorConfig): FileSpec {
    val packageName = generatorConfig.packageName
    val className = generatorConfig.className

    val nothingClassName = ClassName("kotlin", "Nothing")
    val modelClassName = ClassName(packageName, className + "Model")
    val eventClassName = if (generatorConfig.isEventGenerated) {
      ClassName(packageName, className + "Event")
    } else {
      nothingClassName
    }
    val effectClassName = if (generatorConfig.isEffectGenerated) {
      ClassName(packageName, className + "Effect")
    } else {
      nothingClassName
    }
    val updateClassName = ClassName(MOBIUS_PACKAGE_NAME, "Update")
    val nextClassName = ClassName(MOBIUS_PACKAGE_NAME, "Next")
    val superInitClass = updateClassName.parameterizedBy(modelClassName, eventClassName, effectClassName)

    val noChange = MemberName(nextClassName, "noChange")

    return FileSpec.builder(packageName, className + "Update")
      .addType(
        TypeSpec.classBuilder(className + "Update")
          .addSuperinterface(superInitClass)
          .addFunction(
            FunSpec.builder("update")
              .addModifiers(KModifier.OVERRIDE)
              .addParameters(listOf(ParameterSpec("model", modelClassName), ParameterSpec("event", eventClassName)))
              .returns(nextClassName.parameterizedBy(modelClassName, effectClassName))
              .addStatement(
                """
                return %M()
              """.trimIndent(), noChange
              )
              .build()
          )
          .build()
      )
      .build()
  }
}
