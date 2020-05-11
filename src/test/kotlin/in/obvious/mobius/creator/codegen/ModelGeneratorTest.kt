package `in`.obvious.mobius.creator.codegen

import `in`.obvious.mobius.creator.model.GeneratorConfig
import `in`.obvious.mobius.creator.model.MobiusComponent
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ModelGeneratorTest {

  @Test
  fun `generate model code correctly`() {
    // given
    val generatorConfig = GeneratorConfig(
      packageName = "in.obvious.login",
      className = "Login",
      mobiusComponents = listOf(MobiusComponent.Model)
    )
    val expectedGeneratedCode = """
      |package in.obvious.login
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
