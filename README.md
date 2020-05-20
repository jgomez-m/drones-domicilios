# Drones for deliveries
This application uses 20 Drones simultaneously and each drone delivers until 3 lunches.

Technolgies used:
-----------------------
1. JDK 8
2. Maven 3
3. Junit 4
4. Log4j 2

Steps to run and test the application:
-----------------------

1. Download the code using: git clone
2. Run the command: `mvn clean compile`
3. Run the command: `mvn clean test`
4. Run command: `mvn exec:java -Dexec.mainClass="com.s4n.drones.Main"`