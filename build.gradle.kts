// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    dependencies {
        classpath(libs.kotlin.serialization)
        classpath(libs.kotlin.gradle.plugin)
    }
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.dagger.hilt.android) apply false
    alias(libs.plugins.devtools.ksp) apply false

}