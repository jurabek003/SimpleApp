plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id ("com.google.dagger.hilt.android")
    id("kotlin-kapt")

}

android {
    namespace = "uz.turgunboyevjurabek.simpleapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "uz.turgunboyevjurabek.simpleapp"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

   buildFeatures {
           viewBinding=true
       }
    
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-auth:22.2.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    /**
     * Dagger - hilt
     */
    implementation("com.google.dagger:hilt-android:2.45")
    kapt("com.google.dagger:hilt-android-compiler:2.45")
    implementation("androidx.fragment:fragment-ktx:1.6.2")

    /**
     * Kotlin Coroutines
     */
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    /**
     * Retrofit2 and Gson
     */
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    /**
     *Okhttp3
     */
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.1")

    /**
     * ViewModel lifecycle
     */
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")


}