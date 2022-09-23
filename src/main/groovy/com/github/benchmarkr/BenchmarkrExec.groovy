package com.github.benchmarkr

import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.JavaExec
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.compile.JavaCompile
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class BenchmarkrExec extends JavaExec {
    public static final String EXTENSION_NAME = "benchmarkConfig"
    private static final Logger logger = LoggerFactory.getLogger(BenchmarkrExec.class)

    BenchmarkrExec() {
        Set<FileCollection> compileUrls = new HashSet<>()

        // add all of the dependencies for the test classes
        try {
            def compileTestJavaTask = getProject()
                    .getTasks()
                    .withType(JavaCompile)
                    .getByName("compileTestJava")
            compileUrls.add(compileTestJavaTask.classpath)
        } catch (Exception ex) {
            getLogger().info("Skipping compileTestJava...", ex)
        }

        // add the test and main sources as dependencies
        SourceSetContainer sourceSets = (SourceSetContainer)getProject().getProperties().get("sourceSets")
        compileUrls.add(sourceSets.getByName(SourceSet.TEST_SOURCE_SET_NAME).output.classesDirs)
        compileUrls.add(sourceSets.getByName(SourceSet.MAIN_SOURCE_SET_NAME).output.classesDirs)

        // set the classpath for the task
        classpath(compileUrls)

        // add resource directories for source and test
        classpath(sourceSets.getByName(SourceSet.MAIN_SOURCE_SET_NAME).output.resourcesDir)
        classpath(sourceSets.getByName(SourceSet.TEST_SOURCE_SET_NAME).output.resourcesDir)
    }

    @Override
    void exec() {
        def configExtension = getProject()
                .getExtensions()
                .getByName(EXTENSION_NAME) as BenchmarkrExtension

        if (configExtension.packageName().isBlank()) {
            def newPackageName = getProject().group.toString()
            logger.info("Overriding package name with ''")
            configExtension.packageName(newPackageName)
        }

        def config = configExtension.config()
        config.entrySet()
                .stream()
                .forEach(e -> logger.info("${e.getKey()}: '${e.getValue()}'"))
        environment(config)

        super.exec()
    }
}
