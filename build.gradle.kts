
buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {

        classpath("com.android.tools.build:gradle:8.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.50")

    }
}