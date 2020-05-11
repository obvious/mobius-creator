package `in`.obvious.mobius.creator.util

object Constants {
  const val MOBIUS_PACKAGE_NAME = "com.spotify.mobius"
  const val RX_PACKAGE_NAME = "io.reactivex"
  const val RX_MOBIUS_PACKAGE_NAME = "$MOBIUS_PACKAGE_NAME.rx2"

  val mobiusArtifacts = listOf(
    Artifact(artifactId = "com.spotify.mobius:mobius-core"),
    Artifact(artifactId = "com.spotify.mobius:mobius-rx2"),
    Artifact(artifactId = "com.spotify.mobius:mobius-android")
  )
}