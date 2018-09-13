# Lock-Free Treiber Stack

[![Build Status](https://travis-ci.com/IST-CONCURRENCY-COURSE-2018/stack-<your_GitHub_account>.svg?token=B2yLGFz6qwxKVjbLm9Ak&branch=master)](https://travis-ci.com/IST-CONCURRENCY-COURSE-2018/stack-<your_GitHub_account>)

Under this task, you need to implement the simplest lock-free stack. 

Please, use [IntelliJ IDEA](https://www.jetbrains.com/idea/) (better) or Eclipse/Netbeans (worse) as a development environment. Do not use vim or emacs!

## Project description
This project includes the following files:

* `Stack.java` contains the stack interface.
* `StackBenchmark.java` contains a JMH concurrent benchmark.

It is simplier to use Java 8 in order not to have problems with modules.

## Task description
You need to modify the `StackImpl` implementation so that it becomes safe to use from multiple threads simultaneously.


## Build and testing


## Submission format
Do the assignment in this repository, and commit (and push!) your solution to submit it. 

Replace `<your_GitHub_account>` in the beginning of this file with your GitHub account before submission (two places: image and build links). This is required to show a build status in Travis.