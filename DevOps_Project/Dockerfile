# Utilisation de l'image OpenJDK 11 depuis Docker Hub
FROM openjdk:11

# Exposition du port de l'application Spring Boot (remplacez le port par celui que vous avez configuré)
EXPOSE 8082

# Copie du fichier JAR de votre application dans le conteneur
ADD DevOps_Project/target/DevOps_Project-2.1.jar timesheet-devops-1.0.jar

# Commande d'exécution de l'application Spring Boot
ENTRYPOINT ["java", "-jar", "/timesheet-devops-1.0.jar"]
