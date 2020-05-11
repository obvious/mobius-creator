package `in`.obvious.mobius.creator.codegen

import `in`.obvious.mobius.creator.model.GeneratorConfig
import `in`.obvious.mobius.creator.model.MobiusComponent
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class EventGeneratorTest {

  @Test
  fun `generate event code correctly`() {
    // given
    val generatorConfig = GeneratorConfig(
      packageName = "in.obvious.login",
      className = "Login",
      mobiusComponents = listOf(MobiusComponent.Event)
    )
    val expectedGeneratedCode = """
      |package in.obvious.login
      |
      |sealed class LoginEvent
      |
    """.trimMargin()

    // when
    val fileSpec = EventGenerator.generate(generatorConfig)

    // then
    assertThat(fileSpec.toString())
      .isEqualTo(expectedGeneratedCode)
  }
}
