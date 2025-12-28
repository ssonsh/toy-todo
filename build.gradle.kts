plugins {
    kotlin("jvm") version "2.0.21" apply false
    kotlin("plugin.spring") version "2.0.21" apply false
    kotlin("plugin.jpa") version "2.0.21" apply false

    id("org.springframework.boot") version "3.4.1" apply false
    id("io.spring.dependency-management") version "1.1.7" apply false
}

allprojects {
    group = "com.todo"
    version = "0.0.1"
    repositories { mavenCentral() }
}

subprojects {
    plugins.withId("org.jetbrains.kotlin.jvm") {
        dependencies {
            add("implementation", platform("org.springframework.boot:spring-boot-dependencies:3.4.1"))
            add("testImplementation", platform("org.springframework.boot:spring-boot-dependencies:3.4.1"))
        }

        extensions.configure<org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension> {
            jvmToolchain(17)
        }
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }
}