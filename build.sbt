lazy val commonSettings = Seq(
  name := "CustomPipelineExample",
  version := "1.0",
  scalaVersion := "2.11.12",
  libraryDependencies += "org.apache.spark" %%  "spark-core" % "2.4.5",
  libraryDependencies += "org.apache.spark" %%  "spark-sql" % "2.4.5",
  libraryDependencies += "org.apache.spark" %% "spark-mllib" % "2.4.5"
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  enablePlugins(AssemblyPlugin)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs@_*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

assemblyJarName in assembly := "CustomPipelineExample.jar"






