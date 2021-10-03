import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    war
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.spring") version "1.5.31"
}

group = "org.yastech"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive:2.5.5")
    implementation("org.springframework.boot:spring-boot-starter-web:2.5.5")
    implementation("org.springframework.boot:spring-boot-starter-webflux:2.5.5")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.5")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.4")
    implementation("org.springframework.boot:spring-boot-starter-websocket:2.5.5")
    implementation("org.springframework.security:spring-security-messaging:5.5.1")
    implementation("com.google.code.gson:gson:2.8.8")
    implementation("commons-codec:commons-codec")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.5.2-native-mt")
    implementation("io.jsonwebtoken:jjwt:0.9.1")
    implementation("org.springframework.boot:spring-boot-starter-security:2.5.5")
    implementation("org.springframework.boot:spring-boot-starter-mail:2.5.5")
    implementation("javax.xml.bind:jaxb-api")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat:2.5.5")
}

tasks.withType<KotlinCompile>
{
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test>
{
    useJUnitPlatform()
}

tasks.processResources {
    dependsOn("copyFrontendToBuild")
}

tasks.bootWar {
    archiveFileName.set("minimal.war")
}

tasks.bootJar {
    archiveFileName.set("minimal.jar")
}

tasks.register<Copy>("copyFrontendToBuild")
{
    dependsOn("npmBuild")
    from("$projectDir/src/client/dist/")
    into("$buildDir/resources/main/static")
}

tasks.register<Exec>("npmBuild")
{
    workingDir("./src/client/")
    commandLine("npm", "run", "build")
}