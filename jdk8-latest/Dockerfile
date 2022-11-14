#
# Build stage
#
FROM maven:3.6.0-jdk-8-alpine AS build
COPY src/ /app/src/
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package

#
# Package stage
#
FROM openjdk:8-jdk-alpine
COPY --from=build /app/target/java-memory-*.jar /app/java-memory.jar
CMD ["sh", "-c", "java -XX:+PrintFlagsFinal -version | grep -Ei 'maxheapsize|maxram' && java -jar /app/java-memory.jar"]
