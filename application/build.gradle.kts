plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    implementation(project(":domain"))

    implementation("org.springframework:spring-context")
    implementation("org.springframework:spring-tx")

    testImplementation("org.junit.jupiter:junit-jupiter")
}