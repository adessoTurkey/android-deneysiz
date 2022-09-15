package plugins

import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.KtlintPlugin

apply<KtlintPlugin>()

val ktlintVersion = "0.41.0"

configure<KtlintExtension> {
    version.set(ktlintVersion)
    debug.set(false)
    verbose.set(true)
    android.set(true)
    outputToConsole.set(true)
    outputColorName.set("RED")
    ignoreFailures.set(false)
    enableExperimentalRules.set(false)
    additionalEditorconfigFile.set(file("$rootDir/.editorconfig"))

    filter {
        exclude("**/generated/**")
        exclude { projectDir.toURI().relativize(it.file.toURI()).path.contains("/test/") }
        include("**/kotlin/**")
    }
}
