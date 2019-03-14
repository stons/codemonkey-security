import org.springframework.boot.gradle.tasks.bundling.BootJar

buildscript {
    val repos by extra { listOf("http://maven.aliyun.com/nexus/content/groups/public",
            "https://jcenter.bintray.com/",
            "http://192.168.1.222:8092/repository/maven-snapshots/",
            "http://central.maven.org/maven2/","http://repo.spring.io/milestone","https://repo.spring.io/snapshot") }
    repositories {
        mavenLocal()
        for (u in repos) { maven(u) }
    }
}

plugins {
    val springBootVersion = "2.1.1.RELEASE"
    java
    idea
    `java-library`
    id("base")
    id("io.spring.dependency-management") version "1.0.6.RELEASE"
    id("org.springframework.boot") version springBootVersion apply false
    `maven-publish`
}



val repos:List<String> by extra
val springCloudVersion = "Finchley.SR2"
allprojects{
    apply(plugin = "io.spring.dependency-management")

    dependencyManagement{
        imports{
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
            mavenBom("io.spring.platform:platform-bom:Cairo-SR6")
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}")
        }
    }
    repositories {
        mavenLocal()
        for (u in repos) { maven(u) }
    }

    tasks{
        "jar"(Jar::class){
            enabled = true
        }
        "bootJar"(BootJar::class){
            enabled = false
        }
        create("sourcesJar",Jar::class){
            classifier = "sources"
            from(sourceSets.main.get().allJava)
        }

        withType(PublishToMavenRepository::class){
            onlyIf {
                (repository == publishing.repositories["hhkj"] &&
                        publication == publishing.publications["binary"])
//                    ||
//                    (repository == publishing.repositories["internal"] &&
//                            publication == publishing.publications["binaryAndSources"])
            }
        }

        withType(PublishToMavenLocal::class){
            onlyIf {
                publication == publishing.publications["binaryAndSources"]
            }
        }
    }

    publishing {
        publications {
            create<MavenPublication>("binary") {
                from(components["java"])
            }
            create<MavenPublication>("binaryAndSources") {
                from(components["java"])
                artifact(tasks["sourcesJar"])
            }
        }
        repositories {
            maven {
                name = "hhkj"
                url = uri("http://192.168.1.222:8092/repository/maven-snapshots/")
                credentials{
                    username = "admin"
                    password = "am@hao1!2"
                }
            }
//            maven {
//                name = "self"
//                url = uri("$buildDir/repos/internal")
//            }
        }
    }
}


subprojects{
    apply(plugin = "java")
    apply(plugin = "idea")
    apply(plugin = "java-library")
    apply(plugin ="base")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin  = "org.springframework.boot")
    tasks{
        "jar"(Jar::class){
            enabled = true
        }
        "bootJar"(BootJar::class){
            enabled = false
        }
    }

    tasks{
        "jar"(Jar::class){
            enabled = true
        }
        "bootJar"(BootJar::class){
            enabled = false
        }
        create("sourcesJar",Jar::class){
            archiveClassifier.set("sources")
            from(sourceSets.main.get().allJava)
        }

        withType(PublishToMavenRepository::class){
            onlyIf {
                (repository == publishing.repositories["hhkj"] &&
                        publication == publishing.publications["binary"])
//                    ||
//                    (repository == publishing.repositories["internal"] &&
//                            publication == publishing.publications["binaryAndSources"])
            }
        }

        withType(PublishToMavenLocal::class){
            onlyIf {
                publication == publishing.publications["binaryAndSources"]
            }
        }
    }

    publishing {
        publications {
            create<MavenPublication>("binary") {
                from(components["java"])
            }
            create<MavenPublication>("binaryAndSources") {
                from(components["java"])
                artifact(tasks["sourcesJar"])
            }
        }
        repositories {
            maven {
                name = "hhkj"
                url = uri("http://192.168.1.222:8092/repository/maven-snapshots/")
                credentials{
                    username = "admin"
                    password = "am@hao1!2"
                }
            }
//            maven {
//                name = "self"
//                url = uri("$buildDir/repos/internal")
//            }
        }
    }
}
val lombokVersion = "1.18.6"
project(":codemonkey-security-core"){
    dependencies{
        api("org.springframework.cloud:spring-cloud-starter-oauth2")
        api("org.springframework.social:spring-social-config")
        api("org.springframework.social:spring-social-core")
        api("org.springframework.social:spring-social-autoconfigure:2.0.0.BUILD-SNAPSHOT")
        api("org.springframework.social:spring-social-security")
        api("org.springframework.social:spring-social-web")
        api("org.springframework.boot:spring-boot-starter-aop")
        api("commons-lang:commons-lang")
        api("commons-io:commons-io")
        compile("org.projectlombok:lombok:${lombokVersion}")
        annotationProcessor("org.projectlombok:lombok:${lombokVersion}")
    }
}


project(":codemonkey-security-browser"){
    dependencies{
        compile(project(":codemonkey-security-core") )
    }
}

project(":codemonkey-security-app"){
    dependencies{
        compile(project(":codemonkey-security-core") )
    }
}

project(":codemonkey-security-simple"){
    dependencies{
        compile(project(":codemonkey-security-browser") )
        compile("com.h2database:h2")

    }

    tasks{
        "bootJar"(BootJar::class){
            enabled = true
        }
    }
}



