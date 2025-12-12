plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.compose)
    id("maven-publish")
}

android {
    namespace = "com.example.mycameraxlibrary"
    compileSdk = 35

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
    // Core AndroidX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation("androidx.activity:activity-compose:1.9.3")

    // Compose UI
    implementation("androidx.compose.ui:ui:1.4.1")
    implementation("androidx.compose.ui:ui-graphics:1.4.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.1")
    implementation("androidx.compose.foundation:foundation:1.4.1")
    implementation("androidx.compose.foundation:foundation-layout:1.4.1")
    implementation("androidx.compose.material:material:1.4.1")
    implementation("androidx.compose.material:material-icons-core:1.4.1")
    implementation("androidx.compose.material:material-icons-extended:1.4.1")
    implementation("androidx.compose.material3:material3:1.2.1")
    implementation("androidx.compose.material3:material3-window-size-class:1.2.1")
    implementation("androidx.compose.ui:ui-tooling:1.4.1")

    // Hilt
    api("com.google.dagger:hilt-android:2.57.2")
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

    // ExoPlayer / Media3
    api("androidx.media3:media3-exoplayer:1.8.0")
    api("androidx.media3:media3-ui:1.8.0")

    // Material Components (optional)
    implementation("com.google.android.material:material:1.6.1")
}

// Maven publishing for JitPack
afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.muhammadwaqasuddin"
                artifactId = "mycameraxlibrary"
                version = "1.2.0"
            }
        }
    }
}
