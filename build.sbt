import com.lightbend.sbt.javaagent.JavaAgent.JavaAgentKeys.javaAgents

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.11"

ThisBuild / versionScheme := Some("early-semver")

lazy val Versions = new {
  final val twitter = "23.11.0"
  final val logback = "1.3.7"
  final val pprint = "0.9.0"
  final val logstashEncoder = "7.2"
  final val jansi = "2.4.1"
  final val javaAgent = "2.13.3"
}


lazy val root = (project in file("."))
  .enablePlugins(JavaAgent, JavaAppPackaging, DockerPlugin, AshScriptPlugin)
  .settings(
    javaAgents += "io.opentelemetry.javaagent" % "opentelemetry-javaagent" % Versions.javaAgent % "runtime"
  )
  .settings(
    name := "FinatraAppInstrumentation",
    idePackagePrefix := Some("com.one2nc.scalafinatrainstrumentation")
  )

ThisBuild / libraryDependencies ++= Seq(
  "com.twitter"        %% "finatra-http-server" % Versions.twitter,
  "com.twitter"        %% "finatra-jackson" % Versions.twitter,
  "com.twitter"        %% "util-core" % Versions.twitter,
  "com.twitter"        %% "util-slf4j-api" % Versions.twitter,
  "com.twitter"        %% "twitter-server-logback-classic" % Versions.twitter,
  //logging
  "com.lihaoyi"         %% "pprint"                   % Versions.pprint,
  "ch.qos.logback"       % "logback-classic"          % Versions.logback,
  "ch.qos.logback"       % "logback-core"             % Versions.logback,
  "net.logstash.logback" % "logstash-logback-encoder" % Versions.logstashEncoder,
  "org.fusesource.jansi" % "jansi"                    % Versions.jansi,
  "io.opentelemetry.javaagent" % "opentelemetry-javaagent" % Versions.javaAgent % "runtime"
)

Test / javaOptions +=  "-Dotel.javaagent.debug=true"
