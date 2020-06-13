package `in`.obvious.mobius.creator.codegen

import `in`.obvious.mobius.creator.model.GeneratorConfig
import `in`.obvious.mobius.creator.model.MobiusComponent
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class InitGeneratorTest {

  @Test
  fun `generate init code correctly`() {
    // given
    val generatorConfig = GeneratorConfig(
      packageName = "in.obvious.login",
      className = "Login",
      addDependencyEnabled = true,
      mobiusComponents = listOf(MobiusComponent.Init)
    )
    val expectedGeneratedCode = """
      |package `in`.obvious.login
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
