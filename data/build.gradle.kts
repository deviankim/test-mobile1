plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.kimwijin.data"
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
}

dependencies {

    // MultiModule
    implementation(project(":common"))
    implementation(project(":domain"))

    implementation(Dependency.Hilt.CORE)
    kapt(Dependency.Hilt.APT)

    implementation(Dependency.Kotlin.COROUTINE_CORE)
    implementation(Dependency.Kotlin.COROUTINE_ANDROID)

    implementation(Dependency.Paging3.RUNTIME)
    implementation(Dependency.Paging3.COMMON)

    implementation(Dependency.Retrofit.CORE)
    implementation(Dependency.Retrofit.CONVERT_GSON)

    implementation(Dependency.OkHttp.LOGGING_INTERCEPTOR)

    implementation(Dependency.Room.RUNTIME)
    implementation(Dependency.Room.KTX)
    kapt(Dependency.Room.APT)

    implementation(Dependency.Scraping.JSOUP)

    implementation(Dependency.KTX.CORE)
    implementation(Dependency.AndroidX.APP_COMPAT)
    testImplementation(AndroidTestDependency.JUNIT)
    androidTestImplementation(AndroidTestDependency.TEST_JUNIT)
    androidTestImplementation(AndroidTestDependency.ESPRESSO_CORE)
}