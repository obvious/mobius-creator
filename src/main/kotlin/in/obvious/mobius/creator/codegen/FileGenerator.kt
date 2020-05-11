package `in`.obvious.mobius.creator.codegen

import `in`.obvious.mobius.creator.model.GeneratorConfig
import com.squareup.kotlinpoet.FileSpec

interface FileGenerator {
  fun generate(generatorConfig: GeneratorConfig): FileSpec
}
