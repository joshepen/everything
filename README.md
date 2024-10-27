# Everything
Everything is a search program I'm making for nicer searching of files in windows, because I feel like it never works the way I want it to.

## Prerequisites
- Java
- Maven

## Build Instructions
Build with `mvn package`. This also creates a .jar file with dependencies to run it anywere.

## Run Instructions
```
mvn exec:java
```
Or
```
java -cp target\classes com.joshepen.everything.main.Everything
```
Or just run the .jar file.