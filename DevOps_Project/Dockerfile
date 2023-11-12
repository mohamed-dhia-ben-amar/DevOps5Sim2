# Use the official OpenJDK 11 image as the base image
FROM openjdk:11

# Set environment variables for Nexus details
ARG NEXUS_URL=http://http://192.168.56.15:8081/repository/maven-releases
ARG NEXUS_REPOSITORY=tn/esprit/DevOps_Project
ARG JAR_VERSION=2.1
ARG JAR_ARTIFACT=DevOps_Project
ARG JAR_FILE=${JAR_ARTIFACT}-${JAR_VERSION}.jar

# Download the JAR from Nexus
ADD ${NEXUS_URL}/${NEXUS_REPOSITORY}/${JAR_VERSION}/${JAR_FILE} /${JAR_FILE}

# Expose the port your Spring Boot application runs on
EXPOSE 8082

# Define the entry point for your application
ENTRYPOINT ["java", "-jar", "/${JAR_FILE}"]
