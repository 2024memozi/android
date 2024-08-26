enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    includeBuild("build-logic")
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
rootProject.name = "memozi"

include(":app")
include(":core:common")
include(":core:buildconfig")
include(":core:network")
include(":core:datastore")
include(":core:ui")
include(":core:designsystem")
include(":core:model")
