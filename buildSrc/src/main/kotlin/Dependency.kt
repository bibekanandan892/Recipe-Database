import org.gradle.api.JavaVersion

object ProjectConfig{
    const val compileSdk = 34
    const val minSdk = 24
    const val targetSdk = 34
    const val kotlinCompilerExtensionVersion = "1.5.12"

    const val jvmTarget = "1.8"
     val javaVersion =JavaVersion.VERSION_1_8
}