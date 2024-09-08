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
        maven(url = "https://devrepo.kakao.com/nexus/content/groups/public/")
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

// domain
include(":domain:memozi")
include(":domain:memo")
include(":domain:auth")
include(":domain:oauth")

// data
include(":data:memozi")
include(":data:auth")
include(":data:oauth")
// local
include(":local:memozi")

// remote
include(":remote:memozi")
include(":remote:auth")

// feature
include(":feature:memo")
include(":feature:login")
include(":feature:navigator")
include(":feature:diary")
include(":local:auth")

