package dev.sasikanth.creator.codegen

import com.google.common.truth.Truth.assertThat
import dev.sasikanth.creator.model.GeneratorConfig
import dev.sasikanth.creator.model.MobiusComponent.Init
import org.junit.Test

class InitGeneratorTest {

  @Test
  fun `generate init code correctly`() {
    // given
    val generatorConfig = GeneratorConfig(
      packageName = "dev.sasikanth.login",
      className = "Login",
      mobiusComponents = listOf(Init)
    )
    val expectedGeneratedCode = """
      |package dev.sasikanth.login
      |
      |import com.spotify.mobius.First
      |import com.spotify.mobius.First.first
      |import com.spotify.mobius.Init
      |
      |class LoginInit : Init<LoginModel, LoginEffect> {
      |  override fun init(model: LoginModel): First<LoginModel, LoginEffect> = first(model)
      |}
      |
    """.trimMargin()

    // when
    val fileSpec = InitGenerator.generate(generatorConfig)

    // then
    assertThat(fileSpec.toString())
      .isEqualTo(expectedGeneratedCode)
  }
}
