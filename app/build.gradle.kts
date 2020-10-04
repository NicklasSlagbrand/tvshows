import org.codehaus.groovy.runtime.IOGroovyMethods
import org.codehaus.groovy.runtime.ProcessGroovyMethods
import java.io.BufferedReader
import java.io.InputStreamReader

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("gnag")
}

androidExtensions {
    isExperimental = true
}

val ktlint: Configuration by configurations.creating

android {
    compileSdkVersion(29)

    defaultConfig {
        applicationId = "com.nicklasslagbrand.baseline"

        minSdkVersion(21)
        targetSdkVersion(29)

        multiDexEnabled = true

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        compileOptions {
            setSourceCompatibility(1.8)
            setTargetCompatibility(1.8)
        }
    }

    signingConfigs {
        create("release") {
            storeFile = file("./keys/debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "$project.rootDir/tools/proguard-rules-debug.pro"
            )
        }
    }
}

dependencies {
    val kotlinVersion = rootProject.extra.get("kotlinVersion")
    val lifecycleVersion = "2.2.0"
    val koinVersion = "2.0.1"
    val coroutinesVersion = "1.3.3"
    val glideVersion = "4.11.0"
    val navVersion = "2.3.0-alpha04"


    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("com.google.android.material:material:1.1.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("com.github.blackbeared:fusion:1.0.3")

    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")

    implementation("com.github.bumptech.glide:glide:$glideVersion")
    kapt("com.github.bumptech.glide:compiler:$glideVersion")

    implementation("jp.wasabeef:glide-transformations:4.1.0")
    implementation("de.hdodenhof:circleimageview:3.1.0")

    implementation("com.jakewharton.timber:timber:4.7.1")
    implementation("org.koin:koin-android-viewmodel:$koinVersion")
    implementation("net.danlew:android.joda:2.10.3")

    implementation("com.squareup.retrofit2:converter-gson:2.8.1")
    implementation("com.squareup.okhttp3:logging-interceptor:4.4.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

    implementation ("android.arch.lifecycle:extensions:1.1.1")

    ktlint("com.pinterest:ktlint:${rootProject.extra.get("ktlint")}")
}

tasks {
    register<JavaExec>("ktlint") {
        group = "verification"
        description = "Check Kotlin code style."
        classpath = ktlint
        main = "com.pinterest.ktlint.Main"
        args = listOf("src/**/*.kt")
        // to generate report in checkstyle format prepend following args:
        // "--reporter=plain", "--reporter=checkstyle,output=${buildDir}/ktlint.xml"
        // see https://github.com/shyiko/ktlint#usage for more
    }

    register<JavaExec>("ktlintFormat") {
        description = "Fix Kotlin code style deviations."
        classpath = ktlint
        main = "com.pinterest.ktlint.Main"
        args = listOf("-F", "src/**/*.kt")
    }
}

fun String.exec(): String {
    val process = Runtime.getRuntime().exec(this)

    val text: String = IOGroovyMethods.getText(
        BufferedReader(InputStreamReader(process.inputStream))
    )
    ProcessGroovyMethods.closeStreams(process)

    return text
}
/*
    Gnag is a Gradle plugin for Android projects that monitors
    and reports on common violations.
    Link: http://www.gnag.watch/
*/
gnag {
    isEnabled = true
    setFailOnError(true)

    ktlint {
        isEnabled = true
        toolVersion(rootProject.extra.get("ktlint") as String)
    }
    androidLint {
        isEnabled = true
        severity("Error")
    }
    detekt {
        isEnabled = true
        reporterConfig(project.file("detekt-config.yml"))
    }
}
