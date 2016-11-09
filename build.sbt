
organization := "org.helianto"

sbtVersion in ThisBuild := "0.13.9"

scalaVersion in ThisBuild := "2.11.8"

lazy val springBootVersion = "1.4.0.RELEASE"

lazy val root = (project in file("."))
  .enablePlugins(JavaServerAppPackaging, UniversalDeployPlugin)
  .enablePlugins(DockerPlugin)
  .settings(
    name := "helianto-echo-mailer",
    mainClass in (Compile, run) := Some("org.helianto.mailer.Application"),
    dockerBaseImage := "azul/zulu-openjdk:8",
    dockerUpdateLatest := true,
    dockerExposedPorts := Seq(8082),
    dockerRepository := Some("iservport")
  )

libraryDependencies ++= Seq(
  "org.projectlombok"                  % "lombok"                         % "1.16.8",
  "org.springframework.boot"           % "spring-boot-starter-web"        % springBootVersion,
  "org.springframework.boot"           % "spring-boot-starter-data-jpa"   % springBootVersion,
  "org.springframework.boot"           % "spring-boot-starter-test"       % springBootVersion % "test",
  "org.springframework.boot"           % "spring-boot-test-autoconfigure" % springBootVersion % "test",
  "org.springframework.boot"           % "spring-boot-starter-security"   % springBootVersion,
  "org.springframework.boot"           % "spring-boot-starter-freemarker" % springBootVersion,
  "org.springframework.boot"           % "spring-boot-starter-actuator"   % springBootVersion,
  "org.springframework.security.oauth" % "spring-security-oauth2"         % "2.0.11.RELEASE",
  "org.springframework.security"       % "spring-security-jwt"            % "1.0.5.RELEASE",
  "com.iservport"                     %% "iservport-message"              % "1.3.0.RELEASE",
  "io.springfox"                       % "springfox-swagger2"             % "2.6.0",
  "io.springfox"                       % "springfox-swagger-ui"           % "2.6.0",
  "io.swagger"                         % "swagger-core"                   % "1.5.10",
  "javax.servlet"  % "javax.servlet-api"    % "3.0.1"                     % "provided",
  "commons-io"     % "commons-io"           % "2.4",
  "com.zaxxer"     % "HikariCP"             % "2.4.3",
  "com.h2database" % "h2"                   % "1.4.192",
  "mysql"          % "mysql-connector-java" % "5.1.26",
  "org.scalactic" %% "scalactic"            % "3.0.0"
)

resolvers in ThisBuild ++= Seq(
  "Helianto Releases"  at "s3://maven.helianto.org/release",
  "Helianto Snapshots" at "s3://maven.helianto.org/snapshot",
  "Helianto Development" at "s3://maven.helianto.org/devel"
)

publishTo in ThisBuild := {
  val helianto = "s3://maven.helianto.org/"
  if (version.value.trim.endsWith("SNAPSHOT"))
    Some("Helianto Snapshots" at helianto + "snapshot")
  else if (version.value.trim.endsWith("RELEASE"))
    Some("Helianto Releases" at helianto + "release")
  else
    Some("Helianto Development"  at helianto + "devel")
}

credentials += Credentials(Path.userHome / ".sbt" / ".s3credentials")

publishMavenStyle := true

licenses in ThisBuild := Seq("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))


