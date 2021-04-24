name := "connect4-scala"

version := "1.0.0"

scalaVersion := "2.12.2"

val sparkVersion = "3.0.1"

libraryDependencies ++= Seq(
  "io.spray" %% "spray-json" % "1.3.3",
  "com.typesafe" % "config" % "1.2.1"

)

resolvers += Classpaths.typesafeReleases

mainClass in(Compile, run) := Some("main.MainClass")
mainClass in(Compile, packageBin) := Some("main.MainClass")

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

