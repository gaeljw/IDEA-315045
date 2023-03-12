scalaVersion := "2.13.10"

lazy val jdkVersion = "11"
scalacOptions += s"-release:$jdkVersion"
javacOptions ++= Seq("-source", jdkVersion, "-target", jdkVersion)

lazy val root = (project in file("."))
  .settings(name := "idea-315045")

libraryDependencies ++= Seq(
  "org.apache.commons" % "commons-email" % "1.5",

  // ---------- Tests ----------

  "org.scalatest" %% "scalatest" % "3.2.15" % Test,
  "org.jvnet.mock-javamail" % "mock-javamail" % "1.12" % Test,
  "com.github.sbt" % "junit-interface" % "0.13.3" % Test
)

resolvers += "Jenkins Repo" at "https://repo.jenkins-ci.org/releases/"