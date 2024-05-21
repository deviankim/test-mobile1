plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.kimwijin.presentation"
    compileSdk = AndroidEnv.ANDROID_COMPILE

    defaultConfig {
        minSdk = AndroidEnv.ANDROID_MIN

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    dataBinding {
        enable = true
    }
}

dependencies {

    // MultiModule
    implementation(project(":common"))
    implementation(project(":domain"))

    implementation(Dependency.Hilt.CORE)
    kapt(Dependency.Hilt.APT)

    implementation(Dependency.Kotlin.COROUTINE_CORE)
    implementation(Dependency.Kotlin.COROUTINE_ANDROID)

    implementation(Dependency.Glide.CORE)
    kapt(Dependency.Glide.APT)

    implementation(Dependency.Navigation.FRAGMENT)
    implementation(Dependency.Navigation.UI)

    implementation(Dependency.KTX.CORE)
    implementation(Dependency.KTX.FRAGMENT)
    implementation(Dependency.KTX.ACTIVITY)
    implementation(Dependency.KTX.LIFECYCLE_VIEWMODEL)
    implementation(Dependency.AndroidX.APP_COMPAT)
    implementation(Dependency.Google.MATERIAL)
    testImplementation(AndroidTestDependency.JUNIT)
    androidTestImplementation(AndroidTestDependency.TEST_JUNIT)
    androidTestImplementation(AndroidTestDependency.ESPRESSO_CORE)
}