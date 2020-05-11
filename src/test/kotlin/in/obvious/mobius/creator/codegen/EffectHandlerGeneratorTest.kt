package `in`.obvious.mobius.creator.codegen

import `in`.obvious.mobius.creator.model.GeneratorConfig
import `in`.obvious.mobius.creator.model.MobiusComponent
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class EffectHandlerGeneratorTest {

  @Test
  fun `generate effect handler code correctly`() {
    // given
    val generatorConfig = GeneratorConfig(
      packageName = "in.obvious.login",
      className = "Login",
      mobiusComponents = listOf(MobiusComponent.Event, MobiusComponent.Effect, MobiusComponent.EffectHandler)
    )
    val expectedGeneratedCode = """
      |package in.obvious.login
      |
      |import com.spotify.mobius.rx2.RxMobius
      |import io.reactivex.ObservableTransformer
      |
      |class LoginEffectHandler {
      |  fun build(): ObservableTransformer<LoginEffect, LoginEvent> = RxMobius
      |      .subtypeEffectHandler<LoginEffect, LoginEvent>()
      |      .build()
      |}
      |
    """.trimMargin()

    // when
    val fileSpec = EffectHandlerGenerator.generate(generatorConfig)

    // then
    assertThat(fileSpec.toString())
      .isEqualTo(expectedGeneratedCode)
  }
}
