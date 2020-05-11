package dev.sasikanth.creator.codegen

import com.google.common.truth.Truth.assertThat
import dev.sasikanth.creator.model.GeneratorConfig
import dev.sasikanth.creator.model.MobiusComponent.Effect
import dev.sasikanth.creator.model.MobiusComponent.EffectHandler
import dev.sasikanth.creator.model.MobiusComponent.Event
import org.junit.Test

class EffectHandlerGeneratorTest {

  @Test
  fun `generate effect handler code correctly`() {
    // given
    val generatorConfig = GeneratorConfig(
      packageName = "dev.sasikanth.login",
      className = "Login",
      mobiusComponents = listOf(Event, Effect, EffectHandler)
    )
    val expectedGeneratedCode = """
      |package dev.sasikanth.login
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
