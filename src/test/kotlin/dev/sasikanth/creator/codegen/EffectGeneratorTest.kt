package dev.sasikanth.creator.codegen

import com.google.common.truth.Truth.assertThat
import dev.sasikanth.creator.model.GeneratorConfig
import dev.sasikanth.creator.model.MobiusComponent.Effect
import org.junit.Test

class EffectGeneratorTest {

  @Test
  fun `generate effect code correctly`() {
    // given
    val generatorConfig = GeneratorConfig(
        packageName = "dev.sasikanth.login",
        className = "Login",
        mobiusComponents = listOf(Effect)
    )
    val expectedGeneratedCode = """
      |package dev.sasikanth.login
      |
      |sealed class LoginEffect
      |
    """.trimMargin()

    // when
    val fileSpec = EffectGenerator.generate(generatorConfig)

    // then
    assertThat(fileSpec.toString())
      .isEqualTo(expectedGeneratedCode)
  }
}
