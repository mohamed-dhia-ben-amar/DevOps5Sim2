# Utilisation de l'image OpenJDK 11 depuis Docker Hub
FROM openjdk:11

# Exposition du port de l'application Spring Boot (remplacez le port par celui que vous avez configuré)
EXPOSE 8082

# Utilisation d'une commande RUN pour télécharger l'artefact avec curl
#RUN curl -o devops-2.1.jar -L http://192.168.164.198:8081/repository/maven-releases/tn/esprit/DevOps_Project/2.1/devops-2.1.jar

WORKDIR /app

# Copy the JAR file from the build context into the container at /app
COPY target/devops-2.1.jar /app/devops-2.1.jar
ENTRYPOINT ["java", "-jar", "devops.jar"]
