plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.student.mylibrary"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
                "consumer-rules.pro"
            )
        }
    }
    java{
        toolchain{
            languageVersion = JavaLanguageVersion.of(17)
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
        viewBinding = true
    }
}

java{
    toolchain{
        languageVersion = JavaLanguageVersion.of(17)
    }
}

java{
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = "com.github.mstVishnu"
            artifactId = "mylibrary"
            version = "1.0.0"

            pom{
                description="DESC"
            }
//            afterEvaluate {
//                from(components["release"])
//            }
        }
    }
    repositories{
        mavenLocal()
    }

//    repositories {
//        maven {
//            name = "localRepo"
//            url = uri("${rootProject.projectDir}/repo")
//        }
//    }
}
dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.activity)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // Retrofit
    implementation (libs.retrofit)

    // Gson Converter for Retrofit
    implementation (libs.converter.gson)

    // OkHttp for networking
    implementation (libs.okhttp)

    // OkHttp Logging Interceptor for logging network requests/responses
    implementation (libs.logging.interceptor)

    //    //Dimen
    implementation(libs.ssp.android)
    implementation(libs.sdp.android)

}