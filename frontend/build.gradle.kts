buildscript {
    dependencies {
        // Sube el Kotlin integrado de AGP 9 a la versión que usa el plugin de Compose.
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.4.21")
    }
}

plugins {
    id("com.android.application") version "9.4.0" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.4.21" apply false
}
