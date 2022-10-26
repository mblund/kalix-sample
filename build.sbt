inThisBuild(
  Seq(
    scalaVersion := "3.0.2",
    scalacOptions ++= Seq(
      "-deprecation",
      "-unchecked",
      "-feature",
      "-encoding",
      "utf8"
    ),
    scalafmtOnCompile := true
  )
)

val organization = sys.env.get("JIB_TARGET_IMAGE_ORGANIZATION").getOrElse(throw new IllegalArgumentException("JIB_TARGET_IMAGE_ORGANIZATION"))

lazy val main =
  project
    .in(file("."))
    .enablePlugins(JibPlugin)
    .settings(
      version := "0.0.1-SNAPSHOT",
      jibOrganization := organization,
      jibBaseImage           := "openjdk:11-jre",
      jibName                := "jib-hello-world",
      jibUseCurrentTimestamp := true,
      jibPlatforms           := Set(JibPlatforms.amd64, JibPlatforms.arm64)
    )
