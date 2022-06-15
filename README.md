![](images/BenchmarkrGradlePlugin.svg)

## Overview

Set standards for your software.

This repository provides a benchmarking framework for Java. See [Benchmarkr CPP](https://github.com/tnweiss/Benchmarkr-cpp)
to get the binaries used to set up ELK indices and upload test results.

The goal of this project is to track **relative performance**, not absolute performance. No two runs, even on the same machine,
will yield the same results. This project attempts to highlight significant trends and major anomalies by allowing
developers to constantly benchmark their software.

The screenshot below shows the default dashboard that comes with this project. It provides basic information about
which tests have outperformed expectations and which have underperformed.

![](https://github.com/tnweiss/Benchmarkr-cpp/raw/master/images/BenchmarkrDashboard.PNG)

The line graph, in the screenshot above, becomes more useful when you filter on a single test. In the screenshot below
we filtered by `TestName : NormalizeDataTest` and this is what our line graph looked like...

![](https://github.com/tnweiss/Benchmarkr-cpp/raw/master/images/BenchmarkrDashboardLineGraph.PNG)

And then our table, gave us some insight into individual results that could then be further filtered and organized.

![](https://github.com/tnweiss/Benchmarkr-cpp/raw/master/images/BenchmarkrDashboardTable.PNG)

## Related Benchmarkr Projects

- [Benchmarkr](https://github.com/tnweiss/Benchmarkr-cpp) -
  Includes source code for the benchmarkr executable and c++ dev libraries
- [Benchmarkr Maven Plugin](https://github.com/tnweiss/benchmarkr-java-maven-plugin) -
  Benchmarkr plugin for maven
- [Benchmarkr Java](https://github.com/tnweiss/benchmarkr-java-gradle-plugin) -
  Benchmarkr dev libraries for java
- [Benchmarkr Jetbrains Plugin](https://github.com/tnweiss/benchmarkr-jetbrains-plugin) -
  Jetbrains plugin for Intellij and CLION that makes it easier to run individual tests from the gutter
- [Benchmarkr Configuration](https://github.com/tnweiss/benchmarkr-configuration) -
  Centralized configuration for elastic dashboards and indices

## Usage

See [Benchmarkr Java - Installing Benchmarkr](https://github.com/tnweiss/benchmarkr-java#installing-benchmarkr)
for instructions on how to incorporate this plugin into your project.
