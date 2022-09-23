package com.github.benchmarkr

import org.gradle.api.Plugin
import org.gradle.api.Project

class BenchmarkrPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        // add the configuration extension
        project.extensions.create(BenchmarkrExec.EXTENSION_NAME, BenchmarkrExtension)

        // add the benchmarking task
        project.task("benchmark",
                type: BenchmarkrExec,
                description: "Run Benchmarking Tests",
                dependsOn: [project.getTasksByName("compileTestJava", true),
                            project.getTasksByName("compileTestGroovy", true)]
        ).configure {
            main BenchmarkrCore.class.getCanonicalName()
        }
    }
}
