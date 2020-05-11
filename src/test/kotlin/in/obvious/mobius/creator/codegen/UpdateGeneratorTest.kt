package `in`.obvious.mobius.creator.codegen

import `in`.obvious.mobius.creator.model.GeneratorConfig
import `in`.obvious.mobius.creator.model.MobiusComponent
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UpdateGeneratorTest {

  @Test
  fun `generate update code correctly`() {
    // given
    val generatorConfig = GeneratorConfig(
      packageName = "in.obvious.login",
      className = "Login",
      mobiusComponents = listOf(MobiusComponent.Event, MobiusComponent.Effect, MobiusComponent.Update)
    )
    val expectedGeneratedCode = """
      |package in.obvious.login
      |
      |import com.spotify.mobius.Next
      |import com.spotify.mobius.Next.noChange
      |import com.spotify.mobius.Update
      |
      |class LoginUpdate : Update<LoginModel, LoginEvent, LoginEffect> {
      |  override fun update(model: LoginModel, event: LoginEvent): Next<LoginModel, LoginEffect> =
      |      noChange()
      |}
      |
    """.trimMargin()

    // when
    val fileSpec = UpdateGenerator.generate(generatorConfig)

    // then
    assertThat(fileSpec.toString())
      .isEqualTo(expectedGeneratedCode)
  }
}
