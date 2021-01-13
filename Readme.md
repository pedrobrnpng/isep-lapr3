# README #

This is the repository template used for student repositories in LAPR Projets.

#Java source files

Java source and test files are located in folder src.

# Maven files #

Pom.xml file controls the project build.
## Observations
In this file, DO NOT EDIT the following elements:

* groupID
* artifactID
* version
* properties

Also, students can only add dependencies to the specified section on this file.

# Eclipse files #

The following files are solely used by Eclipse IDE:

* .classpath
* .project

# IntelliJ Idea IDE files #

The following folder is solely used by Intellij Idea IDE :

* .idea

## How was the .gitignore file generated? ##
.gitignore file was generated based on https://www.gitignore.io/ with the following keywords:
  - Java
  - Maven
  - Eclipse
  - NetBeans
  - Intellij

## Who do I talk to? ##
In case you have any problem, please email Nuno Bettencourt (nmb@isep.ipp.pt).

## How do I use Maven? ##

### How to run unit tests? ###
Execute the "test" goals.
`$ mvn test`

### How to generate the javadoc for source code? ###
Execute the "javadoc:javadoc" goal.

`$ mvn javadoc:javadoc`

This generates the source code javadoc in folder "target/site/apidocs/index.html".

### How to generate the javadoc for test cases code? ###
Execute the "javadoc:test-javadoc" goal.

`$ mvn javadoc:test-javadoc`

This generates the test cases javadoc in folder "target/site/testapidocs/index.html".

### How to generate Jacoco's Code Coverage Report? ###
Execute the "jacoco:report" goal.

`$ mvn test jacoco:report`

This generates a jacoco code coverage report in folder "target/site/jacoco/index.html".

### How to generate PIT Mutation Code Coverage? ###
Execute the "org.pitest:pitest-maven:mutationCoverage" goal.

`$ mvn test org.pitest:pitest-maven:mutationCoverage`

This generates a PIT Mutation coverage report in folder "target/pit-reports/YYYYMMDDHHMI".

### How to combine different maven goals in one step? ###
You can combine different maven goals in the same command. For example, to locally run your project just like on jenkins, use:

`$ mvn clean test jacoco:report org.pitest:pitest-maven:mutationCoverage`

### How to perform a faster pit mutation analysis ###
Do not clean build => remove "clean"

Reuse the previous report => add "-Dsonar.pitest.mode=reuseReport"

Use more threads to perform the analysis. The number is dependent on each computer CPU => add "-Dthreads=4"

Temporarily remove timestamps from reports.

Example:

`mvn test jacoco:report org.pitest:pitest-maven:mutationCoverage -DhistoryInputFile=target/fasterPitMutationTesting-history.txt -DhistoryOutputFile=target/fasterPitMutationTesting-history.txt -Dsonar.pitest.mode=reuseReport -Dthreads=4 -DtimestampedReports=false`

# Oracle repository

If you get the following error:

```
[ERROR] Failed to execute goal on project 
bike-sharing: Could not resolve dependencies for project 
lapr3:bike-sharing:jar:1.0-SNAPSHOT: 
Failed to collect dependencies at 
com.oracle.jdbc:ojdbc7:jar:12.1.0.2: 
Failed to read artifact descriptor for 
com.oracle.jdbc:ojdbc7:jar:12.1.0.2: 
Could not transfer artifact 
com.oracle.jdbc:ojdbc7:pom:12.1.0.2 
from/to maven.oracle.com (https://maven.oracle.com): 
Not authorized , ReasonPhrase:Authorization Required. 
-> [Help 1]
```

Follow these steps:

https://blogs.oracle.com/dev2dev/get-oracle-jdbc-drivers-and-ucp-from-oracle-maven-repository-without-ides

You do not need to set a proxy.

You can use existing dummy Oracle credentials available at http://bugmenot.com.

Wiki

* [Domain Model](wiki/Domain Model/DomainModel.md)

* [Relational Model](wiki/Relational Model/MR.png)

* [Use Case Diagram](wiki/Use Case Diagram/UCD.md)

* [Add Parks](wiki/UC19-AddParks/UC19-MD.md)

* [Change Parks](wiki/UC21-ChangeParks/UC21-MD.md)

* [See Parks](wiki/UC139-SeeParks/UC139-MD.md)

* [Get Park Report](wiki/UC230-GetParkReport/UC230-MD.md)

* [Remove Parks](wiki/UC20-RemovePark/UC20-MD.md)

* [Change Park Availability](wiki/UC253-ChangeParkAvailability/UC253-MD.md)

* [Add Vehicles](wiki/UC16-AddVehicles/UC16-MD.md)

* [Remove Vehicle](wiki/UC17-RemoveVehicle/UC17-MD.md)

* [Change Vehicle](wiki/UC18-ChangeVehicle/UC18-MD.md)

* [See Vehicles](wiki/UC140-SeeVehicles/UC140-MD.md)

* [Receive Scooter Autonomy Report](wiki/UC231-ReceiveScooterAutonomyReport/UC231-MD.md)

* [Receive Unlocked Vehicles Report](wiki/UC232-ReceiveUnlockedVehiclesReport/UC232-MD.md)

* [Insert Personal Informaton](wiki/UC13-InsertPersonalInformation/UC13-MD.md)

* [Insert Credit Card](wiki/UC14-InsertCreditCard/UC14-MD.md)

* [Pay Registration Fee](wiki/UC15-PayRegistrationFee/UC15-MD.md)

* [Pay Monthly Fee](wiki/UC28-PayMonthlyFee/UC28-MD.md)

* [Check User Information](wiki/UC22-CheckUserInformation/UC22-MD.md)

* [Change User Information](wiki/UC23-ChangeUserInformation/UC23-MD.md)

* [Manage Points](wiki/UC25-ManagePoints/UC25-MD.md)

* [Check park vehicles](wiki/UC27-CheckParkVehicles/UC27-MD.md)

* [Request a vehicle](wiki/UC32-RequestAVehicle/UC32-MD.md)

* [Request Locking/Unlocking History of Vehicles](wiki/UC190-RequestLockingUnlockingHistoryOfVehicles/UC190-MD.md)

* [Check free parking spaces](wiki/UC124-CheckFreeParkingSpaces/UC124-MD.md)

* [Check Nearest Parks](wiki/UC24-CheckNearestParks/UC24-MD.md)

* [Receive Most Energetically Efficient Route Between Two Parks](wiki/UC181-ReceiveMostEnergaticallyEfficientRouteBetweenTwoParks/UC181-MD.md)

* [Shortest Route Beetween Parks That Passes Through POIs](wiki/UC182 -Create the shortest route between two parks that goes by certain interest points/UC182-MD.md)

* [Most Energetically Efficient Route That Passes Through POIs](wiki/UC183-MostEnergyEfficientRouteWithPOIs/UC183-MD.md)

* [Calculate Trip Cost](wiki/UC228-CalculateTripCost/UC228-MD.md)

* [Login](wiki/UC134-Login/UC134-MD.md)

* [Load Points of interest](wiki/UC177-Load Points of interest/UC177-MD.md)

* [Shortest path between two parks](wiki/UC180 -ShortestPathbetweentwoparks/UC180-MD.md)

* [Receive Email With Information](wiki/UC30-ReceiveEmailWithInformation/UC30-MD.md)



