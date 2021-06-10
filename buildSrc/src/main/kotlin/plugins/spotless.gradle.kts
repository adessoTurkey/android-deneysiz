package plugins

import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessPlugin

apply<SpotlessPlugin>()

configure<SpotlessExtension> {
    kotlin {
        target("**/*.kt")
        targetExclude("**/build/")
        targetExclude("bin/**/*.kt")
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }

    kotlinGradle {
        target("*.gradle.kts")
    }
}
