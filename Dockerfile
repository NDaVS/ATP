FROM openjdk:11
LABEL authors="Admin"
COPY target/*.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
