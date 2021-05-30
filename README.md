# CustomPipelineExample

## Overview

This project provides an example of the implementation of custom transformers and estimators based on Apache Spark MLlib. 
The custom transformer adds a new field of features, which is the result of the sum of 2 already existing fields. 
The custom estimator calls the Python training file via rdd.pipe. Logistic regression was used for training.

## Project Structure

    .
    └───CustomPipelineExample                           # Project folder
        ├──project
        │  ├──build.properties                          # Build properties
        │  └──plugins.sbt                               # Build plugin: assembly for fat-jar              
        ├──src
        │  └──main
        │     ├──python
        │     │  ├──requirments.txt                     # Required python packages
        │     │  ├──test.py                             # python file for running model
        │     │  └──train.py                            # python file for fitting model
        │     └──scala
        │        ├──org.apache.spark.ml.feature         # This folder hierarchy is necessary for the correct operation of custom transformers and estimators
        │        │  ├──CustomEstimator.scala            # Custom estimator, which using python files for fitting and running model
        │        │  └──CustomTransformer.scala          # Custom transformer, which creating new field feature3, as a sum of feature1 and feature2
        │        ├──SimplepipelineExample.scala         # Example of standard spark ml-pipeline
        │        ├──Test.scala                          # Test pipeline
        │        └──Train.scala                         # Train pipeline 
        ├──build.sbt                                    # Build properties   
        └──README.md                                    # Awesome README


## Quickstart Guide

1. First of all you need to install Scala 2.11.12 (https://www.scala-lang.org/download/), sbt (https://www.scala-sbt.org/download.html) and Python 3 (https://www.python.org/downloads/).
2. You can run the example locally, to do this you need to install Spark: https://spark.apache.org/downloads.html. This example was tested on 2.4.5 versions of Spark.
3. Go to project folder: `cd CustomPipelineExample`
4. In the files `src/main/python/test.py` and `src/main/python/train.py` on 1 line, specify the path to your Python interpreter if necessary (now it's `#!/usr/bin/python3`).
5. Install the necessary libraries for Python using the command: `path/to/pip3 install -r src/main/python/requirments.txt`.
6. Build the project with the command: `sbt assembly`.
7. After build, in the target folder there will be a file `CustomPipelineExample.jar`.
8. To run an example of a regular pipeline: `spark-submit --class "SimplePipelineExample" target/scala-2.11/CustomPipelineExample.jar`
9. To run a custom pipeline example: `spark-submit --class "Train" target/scala-2.11/CustomPipelineExample.jar` and then `spark-submit --class "Test" target/scala-2.11/CustomPipelineExample.jar`