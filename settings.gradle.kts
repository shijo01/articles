enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Articles"
include(":app")
include(":core")
include(":core:network")
include(":features")
include(":features:articles")
include(":features:articles:data")
include(":features:articles:domain")
include(":features:articles:ui")
