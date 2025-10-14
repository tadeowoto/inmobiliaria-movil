
plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.inmobiliaria_movil"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.inmobiliaria_movil"
        minSdk = 36
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation("com.google.android.material:material:1.12.0")
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.legacy.support.v4)
    implementation(libs.play.services.maps)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.lifecycle.viewmodel)
    implementation("com.github.bumptech.glide:glide:4.14.2")
    implementation(libs.recyclerview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}