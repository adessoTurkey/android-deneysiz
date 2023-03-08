import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

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

    signingConfigs {
        create("release") {
            storeFile = rootProject.file("upload-keystore.jks")
            storePassword = gradleLocalProperties(rootDir).getProperty("storePassword")
            keyAlias = gradleLocalProperties(rootDir).getProperty("keyAlias")
            keyPassword = gradleLocalProperties(rootDir).getProperty("keyPassword")
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            resValue("string", "app_name", "Deneysiz-Dev")
            val baseUrlDev: String by project
            buildConfigField("String", "BASE_URL", baseUrlDev)
        }

        release {
            isMinifyEnabled = true
            isShrinkResources = true
            resValue("string", "app_name", "Deneysiz")
            val baseUrlPrd: String by project
            buildConfigField("String", "BASE_URL", baseUrlPrd)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            val proguardRules = fileTree("proguard") {
                include("*.pro")
            }
            proguardFiles(*proguardRules.toList().toTypedArray())
            signingConfig = signingConfigs.getByName("release")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs = listOf(
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xopt-in=androidx.compose.material.ExperimentalMaterialApi",
            "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xopt-in=androidx.compose.material.ExperimentalMaterialApi",
            "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xopt-in=kotlinx.coroutines.FlowPreview"
        )
    }

    buildFeatures { compose = true }

    composeOptions { kotlinCompilerExtensionVersion = Dependencies.AndroidX.Compose.version }

    lint {
        abortOnError = true
        absolutePaths = false
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
    implementation(Dependencies.AndroidX.Navigation.common)
    implementation(Dependencies.AndroidX.appStartup)
    implementation(Dependencies.AndroidX.splash)
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

    implementation(Dependencies.lottie)

    debugImplementation(Dependencies.chucker)
    releaseImplementation(Dependencies.chuckerNoOp)
    debugImplementation(Dependencies.leakCanary)

    implementation(platform(Dependencies.Firebase.firebaseBom))
    implementation(Dependencies.Firebase.analytics)
    implementation(Dependencies.Firebase.crashlytics)

    implementation(Dependencies.Room.room)
    kapt(Dependencies.Room.roomCompiler)

    testImplementation(Dependencies.Test.junit)
    testImplementation(Dependencies.KotlinX.Coroutines.test)
    androidTestImplementation(Dependencies.Test.junitAndroidX)
    androidTestImplementation(Dependencies.AndroidX.Test.core)
    androidTestImplementation(Dependencies.AndroidX.Test.espressoCore)
    androidTestImplementation(Dependencies.AndroidX.Test.rules)
    androidTestImplementation(Dependencies.AndroidX.Test.Ext.junit)
    androidTestImplementation(Dependencies.AndroidX.Compose.uiTest)
}

if (file("google-services.json").exists()) {
    apply(plugin = "com.google.gms.google-services")
    apply(plugin = "com.google.firebase.crashlytics")
}
