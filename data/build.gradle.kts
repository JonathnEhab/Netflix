import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")


}
val properties = Properties().apply {
    load(File(rootProject.projectDir, "local.properties").inputStream())
}
android {
    namespace = "com.example.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    buildFeatures {
        buildConfig = true

    }
    buildTypes {
        debug {
            buildConfigField("String","MODIES_BASE_URL",
                "\"${properties.getProperty("MOVIEDB_BASE_URL")}\"")
            buildConfigField("String","API_KEY","\"${properties.getProperty("API_KEY")}\"")
            buildConfigField("String","IMAGE_URL","\"${properties.getProperty("IMAGE_URL")}\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String","MODIES_BASE_URL",
                "\"${properties.getProperty("MOVIEDB_BASE_URL")}\"")
            buildConfigField("String","API_KEY","\"${properties.getProperty("API_KEY")}\"")

            buildConfigField("String","IMAGE_URL","\"${properties.getProperty("IMAGE_URL")}\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.firebase.crashlytics.buildtools)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(project(":domain"))



    implementation ("com.squareup.retrofit2:retrofit:2.9.0")


    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")


    implementation ("com.squareup.okhttp3:okhttp:4.9.3")

    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.3")

    // Coroutine
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1-native-mt")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.github.akarnokd:rxjava3-retrofit-adapter:3.0.0")

    // ViewModelScope
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
    // LifecycleScope
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-android-compiler:2.50")


    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")


    implementation("com.squareup.okhttp3:okhttp:4.9.3")

    //room
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:$room_version")
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")

    implementation ("com.google.code.gson:gson:2.8.7")
}
