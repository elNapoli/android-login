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
    compileSdk = 36

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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }

    // Configurar publicación solo para tus variantes
    publishing {
        singleVariant("development") {
            withSourcesJar()
        }
        singleVariant("production") {
            withSourcesJar()
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
    implementation(libs.timber)

    "developmentImplementation"(libs.bundles.napoli.development)
    "productionImplementation"(libs.bundles.napoli.production)
}

afterEvaluate {
    publishing {
        publications {
            // Solo publicamos development y production
            create<MavenPublication>("development") {
                from(components["development"])

                groupId = "com.baldomeronap.android"
                artifactId = "login"
                version = "$libraryVersion-development"

                pom {
                    name.set("Android Base")
                    description.set("Napoli's Android Login - Development")
                }
            }

            create<MavenPublication>("production") {
                from(components["production"])

                groupId = "com.baldomeronap.android"
                artifactId = "login"
                version = "$libraryVersion-production"

                pom {
                    name.set("Android login")
                    description.set("Napoli's Android Login - Production")
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