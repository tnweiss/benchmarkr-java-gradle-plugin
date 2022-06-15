package com.github.benchmarkr

import org.gradle.api.Plugin
import org.gradle.api.Project

class BenchmarkrPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.task("benchmark",  type: Benchmarkr)
    }
}
