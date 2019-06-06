# CleanStrikeGame

Implementation using Java. Use JRE8 and JDK8

Gradle - for building projects.

## Setting up

#### Requirements
* JRE-8 (!!!)

#### External Dependency
1. JUnit - Unit testing
2. Slf4j-Log4j - Logging
3. CommonCollections (Apache) - for CircularLinked list.
4. I'm using shadowJar gradle plugin to package dependency into the JAR as a single file.

### Setting up for Eclipse: run
``` ./gradlew cleanE eclipse ``` and import project
or import as a gradle project. I do not have the .idea plugin setup but it should work either way.


## Running the Project

run the ./runProgram.sh in the directory, that script should build and run the .jar file.

#### Test-cases:

I've covered 100% for all the data classes. Unfortunately, the problem space is too big (the game moves ie) and I have tried to be thorough with testing the functions.


If you want to know more on what goes on behind the scenes
in src/main/resouces/log4j.properties, replace
log4j.rootLogger=INFO, A1
with
log4j.rootLogger=DEBUG, A1
(This will print out all the debug logs).
