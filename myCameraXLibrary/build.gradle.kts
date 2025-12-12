
plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.android)
    // ❌ Remove this line - compose plugin doesn't exist for Kotlin 1.9.x
    // id("org.jetbrains.kotlin.plugin.compose")
    id("maven-publish")
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.mycameraxlibrary"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "2.0.21"
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2024.09.00")
    implementation(composeBom)
    // Core AndroidX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    // Compose
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.activity.compose)

    // Hilt - Use 'api' so consumers get the dependency
    api("com.google.dagger:hilt-android:2.57.2")  // ✅ Match version with project
    kapt("com.google.dagger:hilt-android-compiler:2.57.2")
    api("androidx.hilt:hilt-navigation-compose:1.2.0")

    // CameraX
    api("androidx.camera:camera-core:1.5.2")
    api("androidx.camera:camera-camera2:1.5.2")
    api("androidx.camera:camera-lifecycle:1.5.2")
    api("androidx.camera:camera-view:1.5.2")

    // ML Kit
    api("com.google.mlkit:segmentation-selfie:16.0.0-beta6")

    // Timber
    api("com.jakewharton.timber:timber:5.0.1")

    // ExoPlayer
    api("androidx.media3:media3-exoplayer:1.8.0")
    api("androidx.media3:media3-ui:1.8.0")

    // Compose Core
    api("androidx.compose.ui:ui:1.7.0")
    api("androidx.compose.ui:ui-graphics:1.7.0")
    api("androidx.compose.ui:ui-tooling-preview:1.7.0")

// Compose Foundation
    api("androidx.compose.foundation:foundation:1.7.0")
    api("androidx.compose.foundation:foundation-layout:1.7.0")

// Compose Material & Material3
    api("androidx.compose.material:material:1.7.0")
    api("androidx.compose.material:material-icons-extended:1.7.0")
    api("androidx.compose.material3:material3:1.3.0")

// Compose Lazy lists
    api("androidx.compose.foundation:foundation:1.7.0")

// Activity Compose
    api("androidx.activity:activity-compose:1.9.3")


}

// Maven publishing for JitPack
afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.muhammadwaqasuddin"
                artifactId = "mycameraxlibrary"
                version = "1.1.6"
            }
        }
    }
}