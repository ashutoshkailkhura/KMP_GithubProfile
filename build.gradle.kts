plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("7.3.0").apply(false)
    id("com.android.library").version("7.3.0").apply(false)
    kotlin("android").version("1.8.10").apply(false)
    kotlin("multiplatform").version("1.7.10").apply(false)
}

buildscript {
    val sqlDelightVersion = "1.5.5"
    dependencies {
        classpath("com.squareup.sqldelight:gradle-plugin:$sqlDelightVersion")
        classpath("com.android.tools.build:gradle:8.0.2")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}