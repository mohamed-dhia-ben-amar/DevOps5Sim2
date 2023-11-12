# Utilisation de l'image OpenJDK 11 depuis Docker Hub
#FROM openjdk:11
#
## Exposition du port de l'application Spring Boot (remplacez le port par celui que vous avez configuré)
#EXPOSE 8082
#
## Utilisation d'une commande RUN pour télécharger l'artefact avec curl
##RUN curl -o devops-2.1.jar -L http://192.168.164.198:8081/repository/maven-releases/tn/esprit/DevOps_Project/2.1/devops-2.1.jar
#
#WORKDIR /app
#
## Copy the JAR file from the build context into the container at /app
#COPY target/devops-2.1.jar /app/devops-2.1.jar
#ENTRYPOINT ["java", "-jar", "devops.jar"]

FROM openjdk:11
# Install curl using apt
RUN apt-get update && \
    apt-get install -y curl && \
    rm -rf /var/lib/apt/lists/*
RUN curl -u admin:nexus -o devops-2.1-SNAPSHOT.jar "http://192.168.164.198:8081/repository/maven-snapshots/tn/esprit/devops/2.1-SNAPSHOT/devops-2.1-20231112.173646-1.jar" -L
ENTRYPOINT ["java","-jar","/devops-2.1-SNAPSHOT.jar"]
EXPOSE 8082