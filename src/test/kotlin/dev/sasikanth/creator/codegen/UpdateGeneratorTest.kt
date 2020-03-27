package dev.sasikanth.creator.codegen

import com.google.common.truth.Truth.assertThat
import dev.sasikanth.creator.model.GeneratorConfig
import dev.sasikanth.creator.model.MobiusComponent.Effect
import dev.sasikanth.creator.model.MobiusComponent.Event
import dev.sasikanth.creator.model.MobiusComponent.Update
import org.junit.Test

class UpdateGeneratorTest {

  @Test
  fun `generate update code correctly`() {
    // given
    val generatorConfig = GeneratorConfig(
      packageName = "dev.sasikanth.login",
      className = "Login",
      mobiusComponents = listOf(Event, Effect, Update)
    )
    val expectedGeneratedCode = """
      |package dev.sasikanth.login
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
