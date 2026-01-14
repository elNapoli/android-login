import java.io.ByteArrayOutputStream

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("maven-publish")
}
val libraryVersion = getVersionName()
fun getVersionName(): String {
    val stdout = ByteArrayOutputStream()
    return try {
        exec {
            commandLine("git", "describe", "--tags", "--abbrev=0")
            standardOutput = stdout
        }

        val version = stdout.toString().trim()
        if (version.startsWith("v")) {
            version.substring(1)
        } else {
            version
        }
    } catch (e: Exception) {
        "1.0.0"
    }
}
android {
    namespace = "com.baldomeronapoli.android.login"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        // Tus build types personalizados
        create("development") {
            isMinifyEnabled = false
        }

        create("production") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }
}
// Deshabilitar las variantes de debug y release
androidComponents {
    beforeVariants(selector().withBuildType("debug")) { variantBuilder ->
        variantBuilder.enable = false
    }
    beforeVariants(selector().withBuildType("release")) { variantBuilder ->
        variantBuilder.enable = false
    }
}
dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.navigation.runtime.ktx)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.bundles.koin)

    "developmentImplementation"(libs.napoli.android.base)
}

afterEvaluate {
    publishing {
        publications {
            // Solo publicamos development y production
            create<MavenPublication>("development") {
                from(components["development"])

                groupId = "com.baldomeronap.android"
                artifactId = "base"
                version = "$libraryVersion-development"

                pom {
                    name.set("Android Base")
                    description.set("Napoli's Android Base - Development")
                    url.set("https://github.com/tuusuario/android-base")

                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }

                    developers {
                        developer {
                            id.set("baldomeronapoli")
                            name.set("Baldomero Napoli")
                            email.set("tu@email.com")
                        }
                    }
                }
            }

            create<MavenPublication>("production") {
                from(components["production"])

                groupId = "com.baldomeronap.android"
                artifactId = "base"
                version = "$libraryVersion-production"

                pom {
                    name.set("Android Base")
                    description.set("Napoli's Android Base - Production")
                    url.set("https://github.com/tuusuario/android-base")

                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }

                    developers {
                        developer {
                            id.set("baldomeronapoli")
                            name.set("Baldomero Napoli")
                            email.set("tu@email.com")
                        }
                    }
                }
            }
        }

        repositories {
            maven {
                name = "Local"
                url = uri("${layout.buildDirectory}/repo")
            }

            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/tuusuario/android-base")
                credentials {
                    username = project.findProperty("gpr.user") as String?
                        ?: System.getenv("GITHUB_USERNAME")
                    password = project.findProperty("gpr.token") as String?
                        ?: System.getenv("GITHUB_TOKEN")
                }
            }
        }
    }
}