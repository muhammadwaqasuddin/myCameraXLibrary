
plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    // ❌ Remove this line - compose plugin doesn't exist for Kotlin 1.9.x
    // id("org.jetbrains.kotlin.plugin.compose")
    id("maven-publish")
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
        kotlinCompilerExtensionVersion = "1.5.15"  // ✅ This is what enables Compose for Kotlin 1.9.x
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
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")

    // Compose
    implementation(platform("androidx.compose:compose-bom:2024.11.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.activity:activity-compose:1.9.3")

    // Hilt - Use 'api' so consumers get the dependency
    api("com.google.dagger:hilt-android:2.51.1")  // ✅ Match version with project
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    api("androidx.hilt:hilt-navigation-compose:1.2.0")

    // CameraX
    api("androidx.camera:camera-core:1.5.0")
    api("androidx.camera:camera-camera2:1.5.0")
    api("androidx.camera:camera-lifecycle:1.5.0")
    api("androidx.camera:camera-view:1.5.0")

    // ML Kit
    api("com.google.mlkit:segmentation-selfie:16.0.0-beta6")

    // Timber
    api("com.jakewharton.timber:timber:4.7.1")

    // ExoPlayer
    api("androidx.media3:media3-exoplayer:1.3.0")
    api("androidx.media3:media3-ui:1.3.0")
}

// Maven publishing for JitPack
afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.muhammadwaqasuddin"
                artifactId = "mycameraxlibrary"
                version = "1.0.9"
            }
        }
    }
}