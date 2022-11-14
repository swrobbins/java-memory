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
FROM openjdk:8u92-jdk-alpine
COPY --from=build /app/target/java-memory-*.jar /app/java-memory.jar
CMD ["sh", "-c", "java -version && java -jar /app/java-memory.jar"]
