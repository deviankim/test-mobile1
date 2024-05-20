plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = AndroidEnv.APPLICATION_ID
    compileSdk = AndroidEnv.ANDROID_COMPILE

    defaultConfig {
        applicationId = AndroidEnv.APPLICATION_ID
        minSdk = AndroidEnv.ANDROID_MIN
        targetSdk = AndroidEnv.ANDROID_TARGET
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // MultiModule
    implementation(project(":common"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))

    implementation(Dependency.Hilt.CORE)
    kapt(Dependency.Hilt.APT)

    implementation(Dependency.Retrofit.CORE)
    implementation(Dependency.Retrofit.CONVERT_GSON)

    implementation(Dependency.OkHttp.LOGGING_INTERCEPTOR)

    implementation(Dependency.Room.RUNTIME)
    implementation(Dependency.Room.KTX)
    kapt(Dependency.Room.APT)

    implementation(Dependency.KTX.CORE)
    testImplementation(AndroidTestDependency.JUNIT)
    androidTestImplementation(AndroidTestDependency.TEST_JUNIT)
    androidTestImplementation(AndroidTestDependency.ESPRESSO_CORE)
}