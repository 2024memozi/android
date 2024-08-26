package com.memozi.convention

import com.android.build.api.dsl.CommonExtension
import com.memozi.convention.extension.getBundle
import com.memozi.convention.extension.implementation
import com.memozi.convention.extension.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureKotlinCoroutine(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        dependencies {
            implementation(libs.getBundle("coroutine"))
        }
    }
}
