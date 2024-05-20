plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

object PluginVersion {
    const val GRADLE = "8.1.2"
    const val KOTLIN = "1.8.21"
    const val HILT = "2.50"
    const val SAFE_ARGS = "2.7.7"
}

dependencies {
    implementation("com.android.tools.build:gradle:${PluginVersion.GRADLE}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginVersion.KOTLIN}")
    implementation("com.google.dagger:hilt-android-gradle-plugin:${PluginVersion.HILT}")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:${PluginVersion.SAFE_ARGS}")
}
