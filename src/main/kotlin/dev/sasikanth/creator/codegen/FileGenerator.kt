package dev.sasikanth.creator.codegen

import com.squareup.kotlinpoet.FileSpec
import dev.sasikanth.creator.model.GeneratorConfig

interface FileGenerator {
  fun generate(generatorConfig: GeneratorConfig): FileSpec
}
