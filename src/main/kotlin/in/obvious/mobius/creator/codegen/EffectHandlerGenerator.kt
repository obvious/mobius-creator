package `in`.obvious.mobius.creator.codegen

import `in`.obvious.mobius.creator.model.GeneratorConfig
import `in`.obvious.mobius.creator.util.Constants.RX_MOBIUS_PACKAGE_NAME
import `in`.obvious.mobius.creator.util.Constants.RX_PACKAGE_NAME
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.TypeSpec

object EffectHandlerGenerator : FileGenerator {
  override fun generate(generatorConfig: GeneratorConfig): FileSpec {
    val packageName = generatorConfig.packageName
    val className = generatorConfig.className

    val effectClassName = ClassName(packageName, className + "Effect")
    val eventClassName = ClassName(packageName, className + "Event")
    val loginEffectHandlerClassName = ClassName(packageName, className + "EffectHandler")
    val transformerClassName = ClassName(RX_PACKAGE_NAME, "ObservableTransformer")
    val rxMobiusClassName = ClassName(RX_MOBIUS_PACKAGE_NAME, "RxMobius")

    return FileSpec.builder(packageName, className + "EffectHandler")
      .addType(
        TypeSpec.classBuilder(loginEffectHandlerClassName)
          .addFunction(
            FunSpec.builder("build")
              .addModifiers(KModifier.PUBLIC)
              .returns(transformerClassName.parameterizedBy(effectClassName, eventClassName))
              .addStatement(
                """
                return %T
                    .subtypeEffectHandler<%T, %T>()
                    .build()
              """.trimIndent(),
                rxMobiusClassName,
                effectClassName,
                eventClassName
              )
              .build()
          )
          .build()
      )
      .build()
  }
}