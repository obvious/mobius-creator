package dev.sasikanth.creator.codegen

import com.google.common.truth.Truth.assertThat
import dev.sasikanth.creator.model.GeneratorConfig
import dev.sasikanth.creator.model.MobiusComponent.Model
import org.junit.Test

class ModelGeneratorTest {

  @Test
  fun `generate model code correctly`() {
    // given
    val generatorConfig = GeneratorConfig(
      packageName = "dev.sasikanth.login",
      className = "Login",
      mobiusComponents = listOf(Model)
    )
    val expectedGeneratedCode = """
      |package dev.sasikanth.login
      |
      |class LoginModel
      |
    """.trimMargin()

    // when
    val fileSpec = ModelGenerator.generate(generatorConfig)

    // then
    assertThat(fileSpec.toString())
      .isEqualTo(expectedGeneratedCode)
  }
}
