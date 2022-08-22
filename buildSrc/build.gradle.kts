plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

object PluginVersions {
    const val gradle = "7.0.4"
    const val kotlinGradle = "1.5.10"
    const val spotless = "5.12.5"
    const val detekt = "1.17.1"
    const val ktlint = "10.1.0"
    const val hilt = "2.36"
    const val serialization = "1.5.10"
    const val googleServices = "4.3.13"
    const val crashlytics = "2.9.1"
}

dependencies {
    implementation("com.android.tools.build:gradle:${PluginVersions.gradle}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginVersions.kotlinGradle}")
    implementation("com.diffplug.spotless:spotless-plugin-gradle:${PluginVersions.spotless}")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${PluginVersions.detekt}")
    implementation("org.jlleitschuh.gradle:ktlint-gradle:${PluginVersions.ktlint}")
    implementation("com.google.dagger:hilt-android-gradle-plugin:${PluginVersions.hilt}")
    implementation("org.jetbrains.kotlin:kotlin-serialization:${PluginVersions.serialization}")
    implementation("com.google.gms:google-services:${PluginVersions.googleServices}")
    implementation("com.google.firebase:firebase-crashlytics-gradle:${PluginVersions.crashlytics}")
}
