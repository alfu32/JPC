/*
 * This file was generated by the Gradle 'init' task.
 *
 * This project uses @Incubating APIs which are subject to change.
 */


plugins {
    id("buildlogic.java-application-conventions")
}


application {
    // Define the main class for the application.
    mainClass = "org.jpc.emulator.App"
}

dependencies {
    implementation(project(":debugger-lib"))
}

// Add debugger-lib's resources to this JAR
tasks.jar {
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }
    // Make sure the debugger-lib is compiled first
    dependsOn(":debugger-lib:classes")

    // Merge debugger-lib's classes into emulator.jar
    from(project(":debugger-lib").the<JavaPluginExtension>().sourceSets["main"].output)

    // Make sure debugger-lib has processed its resources first
    dependsOn(":debugger-lib:processResources")

    // Copy the output of debugger-lib's main resources into our jar.
    // By default, Gradle places those processed resources under build/resources/main.
    from("${project(":debugger-lib").buildDir}/resources/main") {
        // If you want them in a subfolder inside the JAR, say "debugger-lib/", do:
        // into("debugger-lib")
        include("**/*")
        // Put them under a folder named "resources" in the jar:
        into("resources")
    }
}
