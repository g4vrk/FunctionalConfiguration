rootProject.name = "FunctionalConfiguration"

include(
    "common",
)

dependencyResolutionManagement {

    versionCatalogs {

        create("libs") {

            from(files("libs.versions.toml"))

        }

    }

}