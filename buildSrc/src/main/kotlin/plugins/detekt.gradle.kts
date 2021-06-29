package plugins

import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import io.gitlab.arturbosch.detekt.extensions.DetektExtension.Companion.DEFAULT_SRC_DIR_JAVA
import io.gitlab.arturbosch.detekt.extensions.DetektExtension.Companion.DEFAULT_SRC_DIR_KOTLIN
import io.gitlab.arturbosch.detekt.extensions.DetektExtension.Companion.DEFAULT_TEST_SRC_DIR_JAVA
import io.gitlab.arturbosch.detekt.extensions.DetektExtension.Companion.DEFAULT_TEST_SRC_DIR_KOTLIN

apply<DetektPlugin>()

configure<DetektExtension> {
    input = objects.fileCollection().from(
        DEFAULT_SRC_DIR_JAVA,
        DEFAULT_TEST_SRC_DIR_JAVA,
        DEFAULT_SRC_DIR_KOTLIN,
        DEFAULT_TEST_SRC_DIR_KOTLIN
    )
    config = files("$rootDir/config/detekt/detekt.yml")

    reports {
        xml.enabled = true
        html.enabled = true
        txt.enabled = true
        sarif.enabled = true
    }
}
