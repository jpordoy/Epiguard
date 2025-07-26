plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.epilabs.epiguard"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.epilabs.epiguard"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }

    buildTypes {
        debug {
            isDebuggable = true
        }
        release {
            isMinifyEnabled = false
            isDebuggable = false
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

    kotlinOptions {
        jvmTarget = "11"
    }

    packaging {
        // Exclude duplicate META-INF/NOTICE.md and META-INF/LICENSE.md files
        resources {
            excludes += "/META-INF/{NOTICE,NOTICE.md,LICENSE,LICENSE.md}"
        }
        // Package libandroidx.graphics.path.so as-is
        jniLibs {
            pickFirsts += "libandroidx.graphics.path.so"
        }
    }

dependencies {
    // Core Android and Kotlin
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.material3)
    implementation(libs.kotlinx.coroutines.android)

    // Jetpack Compose
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.coil.compose)
    implementation(libs.androidx.ui.tooling.preview.android)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.text.google.fonts)
    implementation(libs.androidx.media3.transformer)
    implementation(libs.play.services.basement)
    debugImplementation(libs.androidx.ui.tooling)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.external.antlr)

    // Camera
    implementation(libs.androidx.camera.core)
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.view)

    // TensorFlow
    implementation(libs.tensorflow.lite)
    implementation(libs.tensorflow.lite.support)

    // Media3
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.ui)
    implementation(libs.androidx.media3.ui.compose)

    // Other
    implementation(libs.android.mail)
    implementation(libs.android.activation)
    implementation(libs.jbcrypt)
    implementation(libs.transport.api)
    implementation(libs.androidx.sqlite)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}
}