# General ProGuard Rules

# Preserve annotations
-keepattributes *Annotation*

# Preserve all class member names in classes that implement Parcelable.
-keepclassmembers class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator CREATOR;
}

# Core Android Libraries
-keep class androidx.** { *; }
-dontwarn androidx.**

# Preserve ViewBinding classes
-keep class **ViewBinding { *; }

# Preserve classes for lifecycle components
-keepclassmembers class * {
    @androidx.lifecycle.OnLifecycleEvent <methods>;
}

# OkHttp
-keep class okhttp3.** { *; }
-dontwarn okhttp3.**
-dontwarn okio.**

# Retrofit
-keep class retrofit2.** { *; }
-dontwarn retrofit2.**
-dontwarn okio.**

# Retrofit and Gson Converter
-keepattributes Signature
-keepattributes Exceptions
-keep class com.google.gson.** { *; }
-keep class **$$GsonTypes { *; }
-keepclassmembers class * {
    @retrofit2.http.* <methods>;
}
-dontwarn retrofit2.converter.gson.**

# Jsoup
-keep class org.jsoup.** { *; }
-dontwarn org.jsoup.nodes.**

# PhotoView
-keep class com.github.chrisbanes.photoview.** { *; }
-dontwarn com.github.chrisbanes.photoview.**

# Google Play Services OSS licenses
-keep class com.google.android.gms.oss.** { *; }
-dontwarn com.google.android.gms.oss.**

# Lifecycle Libraries
-keep class androidx.lifecycle.** { *; }
-dontwarn androidx.lifecycle.**

# Testing Libraries
-dontwarn androidx.test.**
-keep class androidx.test.espresso.** { *; }
-keep class androidx.test.runner.** { *; }
-dontwarn androidx.test.espresso.**

# Preserve the line number information for debugging stack traces.
-keepattributes SourceFile,LineNumberTable

# Optionally, hide the original source file name.
-renamesourcefileattribute SourceFile

# Additional custom rules (if any)
# You can add custom rules specific to your project's unique requirements here.
