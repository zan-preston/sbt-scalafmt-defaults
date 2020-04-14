ThisBuild / scalaVersion := "2.12.10"
ThisBuild / organization := "com.alejandrohdezma"

Global / onChangedBuildSource := ReloadOnSourceChanges

addCommandAlias("ci-test", "scalafmtCheckAll; scalafmtSbtCheck; mdoc; scripted")
addCommandAlias("ci-docs", "mdoc; headerCreateAll")

skip in publish := true

lazy val docs = project
  .in(file("sbt-scalafmt-defaults-docs"))
  .enablePlugins(MdocPlugin)
  .settings(skip in publish := true)
  .settings(mdocOut := file("."))

lazy val `sbt-scalafmt-defaults` = project
  .enablePlugins(SbtPlugin)
  .settings(scriptedLaunchOpts += s"-Dplugin.version=${version.value}")
  .settings(Compile / unmanagedResources += baseDirectory.value.getParentFile / ".scalafmt.conf")
