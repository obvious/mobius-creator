package dev.sasikanth.creator.codegen

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.TypeSpec
import dev.sasikanth.creator.model.GeneratorConfig
import dev.sasikanth.creator.util.Constants.MOBIUS_PACKAGE_NAME

object InitGenerator : FileGenerator {

  override fun generate(generatorConfig: GeneratorConfig): FileSpec {
    val packageName = generatorConfig.packageName
    val className = generatorConfig.className

    val modelClassName = ClassName(packageName, className + "Model")
    val effectClassName = ClassName(packageName, className + "Effect")
    val initClassName = ClassName(MOBIUS_PACKAGE_NAME, "Init")
    val firstClassName = ClassName(MOBIUS_PACKAGE_NAME, "First")
    val superInitClass = initClassName.parameterizedBy(modelClassName, effectClassName)

    val first = MemberName(firstClassName, "first")

    return FileSpec.builder(packageName, className + "Init")
      .addType(
        TypeSpec.classBuilder(className + "Init")
          .addSuperinterface(superInitClass)
          .addFunction(
            FunSpec.builder("init")
              .addModifiers(KModifier.OVERRIDE)
              .addParameter("model", modelClassName)
              .returns(firstClassName.parameterizedBy(modelClassName, effectClassName))
              .addStatement(
                """
                return %M(model)
              """.trimIndent(), first
              )
              .build()
          )
          .build()
      )
      .build()
  }
}
