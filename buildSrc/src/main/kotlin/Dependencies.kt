object Dependencies {

    object Kotlin {
        private const val version = "1.5.10"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.3.0"
        const val coreKtx = "androidx.core:core-ktx:1.6.0-beta02"
        const val appStartup = "androidx.startup:startup-runtime:1.0.0"
        const val activityCompose = "androidx.activity:activity-compose:1.3.0-beta01"

        object Compose {
            const val version = "1.0.0-beta09"
            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val layout = "androidx.compose.foundation:foundation-layout:$version"
            const val material = "androidx.compose.material:material:$version"
            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:$version"
            const val tooling = "androidx.compose.ui:ui-tooling:$version"
            const val test = "androidx.compose.ui:ui-test:$version"
            const val uiTest = "androidx.compose.ui:ui-test-junit4:$version"
            const val uiUtil = "androidx.compose.ui:ui-util:$version"
        }

        object Navigation {
            private const val version = "2.4.0-alpha02"
            const val compose = "androidx.navigation:navigation-compose:$version"
        }

        object Test {
            private const val version = "1.3.0"
            const val core = "androidx.test:core:$version"
            const val rules = "androidx.test:rules:$version"

            object Ext {
                private const val version = "1.1.2"
                const val junit = "androidx.test.ext:junit-ktx:$version"
            }

            const val espressoCore = "androidx.test.espresso:espresso-core:3.3.0"
        }

        object Lifecycle {
            private const val version = "2.4.0-alpha01"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val viewModelCompose =
                "androidx.lifecycle:lifecycle-viewmodel-compose" + ":1.0.0-alpha06"
        }
    }

    object KotlinX {
        object Coroutines {
            private const val version = "1.4.2"
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
            const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        }

        object Serialization {
            private const val version = "1.2.1"
            const val json = "org.jetbrains.kotlinx:kotlinx-serialization-json:$version"
        }
    }

    object Dagger {
        private const val version = "2.36"
        const val hilt = "com.google.dagger:hilt-android:$version"
        const val hiltKapt = "com.google.dagger:hilt-android-compiler:$version"
    }

    const val material = "com.google.android.material:material:1.3.0"

    object Test {
        const val junit = "junit:junit:4.13"
        const val junitAndroidX = "androidx.test.ext:junit:1.1.2"
        const val espressoCore = "androidx.test.espresso:espresso-core:3.3.0"
    }

    object Accompanist {
        private const val version = "0.11.0"
        const val coil = "com.google.accompanist:accompanist-coil:$version"
        const val insets = "com.google.accompanist:accompanist-insets:$version"
    }

    const val timber = "com.jakewharton.timber:timber:4.7.1"
    const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
    const val retrofitSerializer = "com.jakewharton.retrofit:retrofit2-" +
        "kotlinx-serialization-converter:0.8.0"
    const val chucker = "com.github.chuckerteam.chucker:library:3.4.0"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:2.7"

    object OkHttp {
        const val bom = "com.squareup.okhttp3:okhttp-bom:4.9.1"
        const val okHttp = "com.squareup.okhttp3:okhttp"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor"
    }
}
