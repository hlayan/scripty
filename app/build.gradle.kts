plugins {
    id("com.android.application")
    id("kotlin-parcelize")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 33
    namespace = "com.hlayan.scripty"

    defaultConfig {
        minSdk = 21
        targetSdk = 33
        versionCode = 2
        versionName = "1.0.1"
        applicationId = "com.hlayan.scripty"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        debug {
            versionNameSuffix = "-debug"
            applicationIdSuffix = ".debug"
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true

            // Debug key signing is available to everyone.
            signingConfig = signingConfigs.getByName("debug")
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
        kotlinCompilerExtensionVersion = "1.3.2" // needed kotlin version 1.7.20
    }
}

dependencies {

    implementation(platform("androidx.compose:compose-bom:2023.01.00"))

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.core:core-splashscreen:1.0.0")

    implementation("com.google.android.material:material:1.7.0")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.17.0")

    implementation("com.jakewharton.timber:timber:5.0.1")

    //Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.41")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    kapt("com.google.dagger:hilt-android-compiler:2.38.1")

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.0")

    debugImplementation("androidx.compose.ui:ui-tooling")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}