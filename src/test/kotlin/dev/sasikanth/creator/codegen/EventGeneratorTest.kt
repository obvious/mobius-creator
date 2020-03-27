package dev.sasikanth.creator.codegen

import com.google.common.truth.Truth.assertThat
import dev.sasikanth.creator.model.GeneratorConfig
import dev.sasikanth.creator.model.MobiusComponent.Event
import org.junit.Test

class EventGeneratorTest {

  @Test
  fun `generate event code correctly`() {
    // given
    val generatorConfig = GeneratorConfig(
      packageName = "dev.sasikanth.login",
      className = "Login",
      mobiusComponents = listOf(Event)
    )
    val expectedGeneratedCode = """
      |package dev.sasikanth.login
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
