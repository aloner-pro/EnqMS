# Use a valid JDK base image
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy the built JAR file
COPY target/*.jar EnqMS.jar

# Expose application port
EXPOSE 9090

# Run the application
ENTRYPOINT ["java", "-jar", "EnqMS.jar"]
