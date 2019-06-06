#!/bin/bash

echo "Building Project"
sh ./gradlew clean build shadowJar

echo "Running Main class"
cd build/libs && java -cp sahaj-cleanStrike-all.jar DriverProgram.GameRunner
# using -ClassPath argument
