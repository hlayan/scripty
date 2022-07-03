plugins {
    id("com.android.application")
    id("kotlin-parcelize")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

val env: MutableMap<String, String> = System.getenv()

android {
    compileSdk = 32
    namespace = "com.hlayan.scripty"

    defaultConfig {
        minSdk = 21
        targetSdk = 32
        versionCode = 2
        versionName = "1.0.1"
        applicationId = "com.hlayan.scripty"
        vectorDrawables.useSupportLibrary = true
    }

    val releaseSigningConfig = signingConfigs.create("release") {
        storeFile = File(env["ReleaseStoreFile"]!!)
        storePassword = env["ReleaseStorePassword"]
        keyAlias = env["ReleaseKeyAlias"]
        keyPassword = env["ReleaseKeyPassword"]
    }

    buildTypes {
        getByName("debug") {
            versionNameSuffix = "-debug"
            applicationIdSuffix = ".debug"
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = releaseSigningConfig
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        useLiveLiterals = false
        kotlinCompilerExtensionVersion = "1.1.1"
    }
}

dependencies {

    val composeVersion = "1.1.1"

    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.compose.material:material-icons-extended:$composeVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.0")
    implementation("androidx.activity:activity-compose:1.5.0")
    implementation("androidx.core:core-splashscreen:1.0.0-rc01")

    implementation("com.google.android.material:material:1.6.1")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.17.0")

    implementation("com.jakewharton.timber:timber:5.0.1")

    //Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.41")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    kapt("com.google.dagger:hilt-android-compiler:2.38.1")

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")

    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}