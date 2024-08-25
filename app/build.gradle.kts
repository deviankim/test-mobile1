plugins {
    id ("com.android.application")
    id ("kotlin-android")
    id ("com.google.android.gms.oss-licenses-plugin") // Plugin to manage OSS licenses
}

android {
    compileSdk = 34

    namespace = "com.rsupport.mobile1.test"

    defaultConfig {
        applicationId = "com.rsupport.mobile1.test"
        minSdk = 21
        targetSdk = 34
        versionCode = project.properties["VERSION_CODE"]?.toString()?.toInt() ?: 1
        versionName = project.properties["VERSION_NAME"]?.toString() ?: "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget ="17"
    }
    viewBinding {
        enable = true
    }
}

dependencies {
    // Kotlin standard library
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.0.0")

    // Core Android libraries
    implementation("androidx.activity:activity-ktx:1.9.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("com.google.android.material:material:1.12.0")

    // Network and data parsing libraries
    implementation("com.squareup.okhttp3:okhttp:4.12.0") // OkHttp for HTTP requests
    implementation("com.squareup.retrofit2:converter-gson:2.11.0") // Gson converter for Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0") // Retrofit for HTTP requests
    implementation("org.jsoup:jsoup:1.16.1") // JSoup for HTML parsing

    // Image handling library
    implementation("com.github.chrisbanes:PhotoView:2.3.0")

    // Google Play Services OSS licenses library
    implementation("com.google.android.gms:play-services-oss-licenses:17.1.0")

    // Lifecycle libraries
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")

    // Testing libraries
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1") // Espresso core library for UI testing
    androidTestImplementation("androidx.test.ext:junit:1.2.1") // AndroidJUnit runner for instrumentation tests
    implementation("androidx.test.espresso:espresso-contrib:3.6.1")
    testImplementation("junit:junit:4.13.2")
}