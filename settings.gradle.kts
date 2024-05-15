pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

val urlOracle by lazy { providers.gradleProperty("urlOracle").getOrElse("defaultUrl") }
val urlOracle2 by lazy { providers.gradleProperty("urlOracle2").getOrElse("defaultUrl") }
val usernameOracle by lazy { providers.gradleProperty("usernameOracle").getOrElse("defaultUser") }
val passwordOracle by lazy { providers.gradleProperty("passwordOracle").getOrElse("defaultPassword") }


dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri(urlOracle)
            credentials {
                username = usernameOracle
                password = passwordOracle
            }
        }
        maven {
            url = uri(urlOracle2)
            credentials {
                username = usernameOracle
                password = passwordOracle
            }
        }
    }
}

rootProject.name = "Base Oracle"
include(":app")
