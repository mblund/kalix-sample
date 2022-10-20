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

lazy val main =
  project
    .in(file("."))
    .enablePlugins(JibPlugin)
    .settings(
      version := "0.0.1-SNAPSHOT",
      jibOrganization := "mblund", // TODO: REPLACE-WITH-YOUR-DOCKER-HUB-ORGANIZATION
      jibBaseImage           := "openjdk:11-jre",
      jibName                := "jib-hello-world",
      jibUseCurrentTimestamp := true,
      jibPlatforms           := Set(JibPlatforms.amd64, JibPlatforms.arm64)
    )
