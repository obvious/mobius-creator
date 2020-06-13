package `in`.obvious.mobius.creator.codegen

import `in`.obvious.mobius.creator.model.GeneratorConfig
import `in`.obvious.mobius.creator.model.MobiusComponent
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class EffectGeneratorTest {

  @Test
  fun `generate effect code correctly`() {
    // given
    val generatorConfig = GeneratorConfig(
      packageName = "in.obvious.login",
      className = "Login",
      addDependencyEnabled = true,
      mobiusComponents = listOf(MobiusComponent.Effect)
    )
    val expectedGeneratedCode = """
      |package `in`.obvious.login
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
