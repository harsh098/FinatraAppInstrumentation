Docker / organizationName := organization.value
Docker / version := version.value

dockerBaseImage := "eclipse-temurin:23.0.2_7-jre-alpine-3.21"
dockerExposedPorts := Seq(8888)
dockerExposedVolumes := Seq("/opt/docker/logs")
dockerRepository := Some("test")


