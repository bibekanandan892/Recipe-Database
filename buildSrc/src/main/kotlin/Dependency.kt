import org.gradle.api.JavaVersion

object ProjectConfig{
    const val COMPILE_SDK = 34
    const val MIN_SDK = 24
    const val TARGET_SDK = 34
    const val KOTLIN_COMPILER_EXTENSION_VERSION = "1.5.12"
    const val JVM_TARGET = "1.8"
    val JAVA_VERSION =JavaVersion.VERSION_1_8
}