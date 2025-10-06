plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)


    // for navigation
    kotlin("plugin.serialization") version "2.0.21"
    alias(libs.plugins.androidx.navigation.safe.args)

    // for room and hilt
    id("com.google.devtools.ksp")

    //for Hilt
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
//    id("com.google.devtools.ksp")
}

android {
    namespace = "pr.peyman.movieapptest"
    compileSdk = 36

    defaultConfig {
        applicationId = "pr.peyman.movieapptest"
        minSdk = 26
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
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    // NestedScrollView
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    //Coroutine
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)

    ///LifeCycle
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    //navigation
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    //Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    // Extention and Corotine for Room
    implementation(libs.androidx.room.ktx)

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // OkHttps
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    //Dagger - Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler.v2561)

    // image loading
    // glide
    implementation(libs.glide)

    //coil
    implementation(libs.coil)
    implementation(libs.coil.network.okhttp)

    //Gson
    implementation(libs.gson)

    //DataStore
    implementation(libs.androidx.datastore.preferences)

    //DynamicSize
    implementation(libs.dynamicsizes)

    // other indicator
    implementation(libs.circleindicator)
}