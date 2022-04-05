# Map Reduce

## Features!

## Desciption
Steps flow of the application (see main file of origin):

1. The application exec from the main fie in origin folder. It get the number of nodes (relate to the number of line in the data.txt file).

2. Split rule: “The number of nodes = the number of line in data.txt file”. The origin will create nodes config accordingly.

3. Each node will initiate a RMI server (see main file, origin).

4. Each node will initiate a socket also.

5. Origin node runs the Split to split the data.txt file bases on the number of lines in data.txt. Send the splitter files to each node.

6. Call() method will then execute each node map execution in each nodes.

7. Callback notify to launch that node has finished.

8. Launch will download the result files via socket.

9. Launch will do the final reduce to get the final result. 

## Enviroment
### Required
 - Java 14.0.2 check [here](https://github.com/harnetlinh/Project-Hyperspectral-Images/tree/test#java)
 - Gradle 7.3.3 check [here](https://github.com/harnetlinh/Project-Hyperspectral-Images/tree/test#gradle)
 ### Setup enviroment
 #### Java
  - To check current java version
```
java --version
```
  - If not exist, download it [here](https://www.oracle.com/java/technologies/javase/jdk14-archive-downloads.html)
  - Setup or Unzip the file
  - Create a new enviroment variable name "JAVA_HOME" with value is directory of JDK14 which you have just downloaded it
  - Add a new PATH system variable with JDK14 folder

#### Gradle
  - To check current java version
```
gradle --version
```
  - If not exist, download it [here](https://gradle.org/install/)
  - Unzip the file
  - Add a new PATH system variable with <Gradle folder>/bin
  

## Run
Open project folder:

### To setup
```
./gradlew
```

### To run project
```
gradle run
```

### To run test
```
gradle test
```

Check the test result in 
```
build/reports/tests/test/
```
