plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization")
    id("kotlin-parcelize")
}

android {
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.testInstrumentationRunner
        vectorDrawables { useSupportLibrary = true }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            val baseUrlDev: String by project
            buildConfigField("String", "BASE_URL", baseUrlDev)
        }

        release {
            isMinifyEnabled = false
            val baseUrlPrd: String by project
            buildConfigField("String", "BASE_URL", baseUrlPrd)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions { jvmTarget = "11" }
    buildFeatures { compose = true }
    composeOptions { kotlinCompilerExtensionVersion = Dependencies.AndroidX.Compose.version }

    lint {
        isAbortOnError = true
        isAbsolutePaths = false
        lintConfig = file("$rootDir/config/lint/lint.xml")
    }
}

dependencies {
    implementation(Dependencies.Kotlin.stdlib)
    implementation(Dependencies.KotlinX.Coroutines.core)
    implementation(Dependencies.KotlinX.Coroutines.android)
    implementation(Dependencies.KotlinX.Serialization.json)

    implementation(Dependencies.AndroidX.activityCompose)
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.appcompat)
    implementation(Dependencies.AndroidX.Lifecycle.livedata)
    implementation(Dependencies.AndroidX.Lifecycle.runtime)
    implementation(Dependencies.AndroidX.Lifecycle.viewModel)
    implementation(Dependencies.AndroidX.Lifecycle.viewModelCompose)
    implementation(Dependencies.AndroidX.Navigation.compose)
    implementation(Dependencies.AndroidX.appStartup)
    implementation(Dependencies.material)

    implementation(Dependencies.AndroidX.Compose.layout)
    implementation(Dependencies.AndroidX.Compose.foundation)
    implementation(Dependencies.AndroidX.Compose.material)
    implementation(Dependencies.AndroidX.Compose.tooling)
    implementation(Dependencies.AndroidX.Compose.uiUtil)
    implementation(Dependencies.AndroidX.Compose.runtime)
    implementation(Dependencies.AndroidX.Compose.runtimeLivedata)

    implementation(Dependencies.Dagger.hilt)
    implementation(Dependencies.Dagger.hiltNavigationCompose)
    kapt(Dependencies.Dagger.hiltKapt)

    implementation(Dependencies.Accompanist.coil)
    implementation(Dependencies.Accompanist.insets)
    implementation(Dependencies.Accompanist.systemUiController)

    implementation(Dependencies.timber)
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.retrofitSerializer)

    implementation(platform(Dependencies.OkHttp.bom))
    implementation(Dependencies.OkHttp.okHttp)
    implementation(Dependencies.OkHttp.loggingInterceptor)

    debugImplementation(Dependencies.chucker)
    debugImplementation(Dependencies.leakCanary)

    testImplementation(Dependencies.Test.junit)
    testImplementation(Dependencies.KotlinX.Coroutines.test)
    androidTestImplementation(Dependencies.Test.junitAndroidX)
    androidTestImplementation(Dependencies.AndroidX.Test.core)
    androidTestImplementation(Dependencies.AndroidX.Test.espressoCore)
    androidTestImplementation(Dependencies.AndroidX.Test.rules)
    androidTestImplementation(Dependencies.AndroidX.Test.Ext.junit)
    androidTestImplementation(Dependencies.AndroidX.Compose.uiTest)
}
