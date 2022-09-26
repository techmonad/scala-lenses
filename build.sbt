import sbtrelease.ReleaseStateTransformations._
import sbtrelease.ReleasePlugin.autoImport._

// -------------------------------------------------------------------------------------------------------------------
// Root Project
// -------------------------------------------------------------------------------------------------------------------
lazy val root = (project in file("."))
  .settings(
    inThisBuild(
      List(
        organization            := "com.techmonal",
        scalaVersion            := "2.13.9",
        scalastyleFailOnError   := true,
        scalastyleFailOnWarning := false,
        scalafmtOnCompile       := true,
      )
    ),
    name := "scala-lenses",
    libraryDependencies ++= dependencies,
  )

lazy val dependencies = Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.10",
  "org.scalatest" %% "scalatest"       % "3.2.11" % Test,
)

scalacOptions ++= Seq(
  "-feature",
  "-unchecked",
  "-language:higherKinds",
  "-language:postfixOps",
  "-deprecation",
)

resolvers ++= Seq(
  Resolver.mavenLocal,
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  "Bintray ".at("https://dl.bintray.com/projectseptemberinc/maven"),
)

releaseProcess := Seq(
  checkSnapshotDependencies,
  inquireVersions,
  // publishing locally in the process
  releaseStepCommandAndRemaining("+publishLocal"),
  releaseStepCommandAndRemaining("+clean"),
  releaseStepCommandAndRemaining("+test"),
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  setNextVersion,
  commitNextVersion,
  pushChanges,
)

addCommandAlias("validate", "; clean; compile; test;")
