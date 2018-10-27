val commonSettings = Seq(
  scalaVersion := "2.12.7",
  organization := "com.github.yoshiyoshifujii.sam",
  libraryDependencies ++= Seq(
    ScalaTest.v3_0_5      % Test
  )
)

val assemblySettings = Seq(
  assemblyMergeStrategy in assembly := {
    case PathList(ps @ _ *) if ps.last endsWith ".properties" => MergeStrategy.first
    case "application.conf"                                   => MergeStrategy.concat
    case x =>
      val oldStrategy = (assemblyMergeStrategy in assembly).value
      oldStrategy(x)
  },
  assemblyJarName in assembly := s"${name.value}-${version.value}.jar",
  publishArtifact in (Compile, packageBin) := false,
  publishArtifact in (Compile, packageSrc) := false,
  publishArtifact in (Compile, packageDoc) := false
)

lazy val `api-core` = (project in file("modules/api-core")).
  settings(commonSettings: _*).
  settings(
    name := "sam-sample-api-core",
    libraryDependencies ++= Seq(
      S8Scala.api,
      S8Scala.logger
    )
  )

lazy val api = (project in file("modules/api")).
  settings(commonSettings: _*).
  settings(assemblySettings: _*).
  settings(
    name := "sam-sample-api",
    libraryDependencies ++= Seq(
    )
  ).
  dependsOn(`api-core`)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "sam-sample"
  )
