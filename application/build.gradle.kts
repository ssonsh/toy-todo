plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    api(project(":domain"))
    implementation(project(":common-lib"))

    implementation("org.springframework:spring-context")
    implementation("org.springframework:spring-tx")
    implementation("org.springframework:spring-web")

    testImplementation("org.junit.jupiter:junit-jupiter")
}