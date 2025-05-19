plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.compose")
    id("com.android.application")
    id("com.google.gms.google-services")
}

kotlin {
    androidTarget()
    jvm("desktop")
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    linuxX64()
    macosX64()
    mingwX64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
                implementation("com.russhwolf:multiplatform-settings-no-arg:0.11.0")
                implementation("app.cash.sqldelight:runtime:2.0.0")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(compose.ui)
                implementation(compose.activity)
                implementation("com.google.android.gms:play-services-ads:22.5.0")
                implementation("com.android.billingclient:billing:6.0.1")
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }

        val iosMain by getting {
            dependencies {
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material3)
            }
        }
    }
}

android {
    namespace = "com.geekneuron.librex"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.6.0"
    }

    signingConfigs {
        release {
            storeFile file("librex-release-key.keystore")
            storePassword "your_store_password"
            keyAlias "your_key_alias"
            keyPassword "your_key_password"
        }
    }

    buildTypes {
        release {
            minifyEnabled true
            proguardFiles getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            signingConfig signingConfigs.release
        }
    }
}
