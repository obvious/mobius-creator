package dev.sasikanth.creator.util

enum class ScopeType {
  MAIN,
  UNIT_TEST
}

data class Artifact(
  val artifactId: String,
  val scope: ScopeType = ScopeType.MAIN
)
