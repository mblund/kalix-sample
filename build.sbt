inThisBuild(
  Seq(
    scalaVersion := "2.13.10",
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

val organization =
  sys.env.get("JIB_TARGET_IMAGE_ORGANIZATION").getOrElse("mblund")

lazy val root = (project in file("."))
  .aggregate(customerRegistry)

lazy val customerRegistry =
  project
    .in(file("customer-registry"))
    .enablePlugins(KalixPlugin, JibPlugin)
    .settings(
      name                   := "customer-registry",
      jibOrganization        := organization,
      jibBaseImage           := "openjdk:11-jre",
      jibName                := "jib-hello-world",
      jibUseCurrentTimestamp := true,
      jibPlatforms           := Set(JibPlatforms.amd64, JibPlatforms.arm64),
      libraryDependencies ++= Seq(
        "org.scalatest" %% "scalatest" % "3.2.14" % Test
      )
    )
