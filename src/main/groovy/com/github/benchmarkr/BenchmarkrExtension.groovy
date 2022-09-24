package com.github.benchmarkr

import groovy.cli.Option

class BenchmarkrExtension {
    private String packageName = BenchmarkrCore.PACKAGE_PROPERTY_DEFAULT
    private String className = BenchmarkrCore.CLASS_PROPERTY_DEFAULT
    private String methodName = BenchmarkrCore.METHOD_PROPERTY_DEFAULT
    private int iterations = Integer.parseInt(BenchmarkrCore.ITERATIONS_PROPERTY_DEFAULT)
    private boolean ignoreFailures = false
    private boolean recordResults = false
    private String console = BenchmarkrCore.CONSOLE_PROPERTY_DEFAULT

    ///////////////////////////////////////////////////// METHODS /////////////////////////////////////////////////////
    Map<String, ?> config() {
        Map<String, ?> data = new HashMap<>()

        data.put(BenchmarkrCore.PACKAGE_PROPERTY, packageName)
        data.put(BenchmarkrCore.CLASS_PROPERTY, className)
        data.put(BenchmarkrCore.METHOD_PROPERTY, methodName)
        data.put(BenchmarkrCore.ITERATIONS_PROPERTY, iterations)
        data.put(BenchmarkrCore.IGNORE_FAILURES_PROPERTY, ignoreFailures)
        data.put(BenchmarkrCore.CONSOLE_PROPERTY, console)
        data.put(BenchmarkrCore.RECORD_PROPERTY, recordResults)

        return data
    }

    //////////////////////////////////////////////// SETTERS / GETTERS ////////////////////////////////////////////////
    @Option(longName = "package-name", shortName = 'p')
    void packageName(String packageName) {
        this.packageName = packageName
    }

    String packageName() {
        return this.packageName
    }

    @Option(longName = "class-name", shortName = 'c')
    void className(String className) {
        this.className = className
    }

    String className() {
        return this.className
    }

    @Option(longName = "method-name", shortName = 'm')
    void methodName(String methodName) {
        this.methodName = methodName
    }

    String methodName() {
        return this.methodName
    }

    @Option(longName = "iterations", shortName = 'i')
    void iterations(String iterations) {
        this.iterations = Integer.parseInt(iterations)
    }

    void iterations(int iterations) {
        this.iterations = iterations
    }

    int iterations() {
        return this.iterations
    }

    @Option(longName = "ignore-failures", shortName = 'f')
    void ignoreFailures(String ignoreFailures) {
        this.ignoreFailures = Boolean.parseBoolean(ignoreFailures)
    }

    void ignoreFailures(boolean ignoreFailures) {
        this.ignoreFailures = ignoreFailures
    }

    boolean ignoreFailures() {
        return this.ignoreFailures
    }

    @Option(longName = "record-results", shortName = 'r')
    void recordResults(String recordResults) {
        this.recordResults = Boolean.parseBoolean(recordResults)
    }

    void recordResults(boolean recordResults) {
        this.recordResults = recordResults
    }

    boolean recordResults() {
        return this.recordResults
    }

    @Option(longName = "console", shortName = 'o')
    void console(String console) {
        this.console = console
    }

    String console() {
        return this.console
    }
}
