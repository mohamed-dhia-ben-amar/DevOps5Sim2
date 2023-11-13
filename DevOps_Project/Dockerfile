# Use the official OpenJDK 11 image as the base image
FROM openjdk:11

# Set environment variables for Nexus details
ARG NEXUS_URL=http://http://192.168.56.15:8081/repository/maven-releases
ARG NEXUS_REPOSITORY=tn/esprit/DevOps_Project
ARG JAR_VERSION=2.1
ARG JAR_ARTIFACT=DevOps_Project
ARG JAR_FILE=${JAR_ARTIFACT}-${JAR_VERSION}.jar
RUN apt-get update && \
    apt-get install -y curl && \
    rm -rf /var/lib/apt/lists/*
# Download the JAR from Nexus
RUN curl -u admin:nexus -o devops-2.1-SNAPSHOT.jar "http://192.168.56.15:8081/repository/maven-releases/tn/esprit/DevOps_Project/2.1/DevOps_Project-2.1.jar" -L
# Expose the port your Spring Boot application runs on
EXPOSE 8089

# Define the entry point for your application
ENTRYPOINT ["java", "-jar", "/devops-2.1-SNAPSHOT.jar"]
