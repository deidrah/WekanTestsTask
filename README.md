# WekanTestsTask
 
## Dependencies
Make sure you have the following stack installed:
* Java SE 12
* IDE (IntelliJ or Eclipse)

## Description
Test suite contains 4 tests, two login and two registration tests for https://wekan.coded.pl/ application.

Login Tests are:
* login with existing user 
* login with non existing user

Registration Tests are:
* register new user
* register existing user


## Instructions
1. Clone the repository `https://github.com/deidrah/WekanTestsTask.git` 
2. Open the project in IDE
3. Build the project
4. Open the console and run on Windows -  `gradlew test`, on MacOS - first run `./gradlew wrapper` and then `./gradlew test`

The test suite should be now running.
To rerun tests simply add `--rerun-tasks` flag to command

## Problems
If you have problems with compatibility try changing the settings of Gradle JVM to using project JDK


