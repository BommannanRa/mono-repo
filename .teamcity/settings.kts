import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.nuGetInstaller
import jetbrains.buildServer.configs.kotlin.triggers.vcs

version = "2022.10"

project {
    buildType(Build)
}

object Build : BuildType({
    name = "Build"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        nuGetInstaller {
            name = "Nuget installation"
            toolPath = "%teamcity.tool.NuGet.CommandLine.DEFAULT%"
            projects = "ASPNETCoreWebAPI/SampleWebApiAspNetCore.sln"
            updatePackages = updateParams {
            }
        }
    }

    triggers {
        vcs {
        }
    }

    features {
        perfmon {
        }
    }
})

object Build : BuildType({
    name = "${DslContext.getParameter("VersionNumber", "Test version")}"
})