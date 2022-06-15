package com.github.benchmarkr

import com.github.benchmarkr.console.Consoles
import com.github.benchmarkr.test.plan.TestPlanParameters
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Benchmarkr extends DefaultTask {
    private static final Logger logger = LoggerFactory.getLogger(Benchmarkr.class)

    @Option(option =  "package-name",
            description = "Top level package where the tests reside eg 'com.example'.")
    @Input
    String packageName = BenchmarkrCore.PACKAGE_PROPERTY_DEFAULT

    @Option(option =  "class",
            description = "A specific class to search for benchmarking tests in.")
    @Input
    String className = BenchmarkrCore.CLASS_PROPERTY_DEFAULT

    @Option(option =  "benchmark",
            description = "The specific benchmarking test to run.")
    @Input
    String methodName = BenchmarkrCore.METHOD_PROPERTY_DEFAULT

    @Input
    int iterations = Integer.parseInt(BenchmarkrCore.ITERATIONS_PROPERTY_DEFAULT)

    @Option(option =  "iterations",
            description = "The number of times to execute the selected tests.")
    void iterations(String iterations) {
        this.iterations = Integer.parseInt(iterations)
    }

    @Option(option =  "console",
            description = "The console type ('System', 'Silent')")
    @Input
    String console = BenchmarkrCore.CONSOLE_PROPERTY_DEFAULT

    @Input
    boolean record = true

    @Option(option =  "record",
            description = "Whether or not to record the result")
    void record(String record) {
        this.record = Boolean.parseBoolean(record)
    }

    @Input
    boolean ignoreFailures = false

    @Option(option =  "ignore-failures",
            description = "Stop execution on benchmarking failure")
    void ignoreFailures(String ignoreFailures) {
        this.ignoreFailures = Boolean.parseBoolean(ignoreFailures)
    }

    @TaskAction
    void benchmark() {
        logger.info("Running Benchmarking Tests")

        TestPlanParameters testPlanParameters = TestPlanParameters.builder()
                .console(Consoles.system())
                .classLoader(getClassLoader())
                .packageName(packageName == null || packageName.isBlank() ? getProject().getGroup().toString() : packageName)
                .className(className)
                .methodName(methodName)
                .iterations(iterations)
                .console(Consoles.from(console))
                .recordResults(record)
                .ignoreFailure(ignoreFailures)
                .build()

        logger.info("Scanning for tests in package " + testPlanParameters.getPackageName())

        if (BenchmarkrCore.main(testPlanParameters) != 0) {
            throw new GradleException("Benchmark tests failed")
        }
    }

    private ClassLoader getClassLoader()
    {
        try {
            SourceSetContainer sourceSets = (SourceSetContainer)getProject().getProperties().get("sourceSets")

            URL[] testOutputUrls = [
                    new File(sourceSets.getByName(SourceSet.TEST_SOURCE_SET_NAME)
                            .output.classesDirs.asPath).toURI().toURL(),
                    new File(sourceSets.getByName(SourceSet.MAIN_SOURCE_SET_NAME)
                            .output.classesDirs.asPath).toURI().toURL()
            ]

            return new URLClassLoader(testOutputUrls, getClass().getClassLoader())
        } catch (Exception ex) {
            throw new GradleException(ex.getMessage())
        }
    }
}
