# Stage 1: Build the JAR using Maven with a supported JDK version
FROM maven:3.9.9-amazoncorretto-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar EnqMS.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "EnqMS.jar"]
