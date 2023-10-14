FROM maven:3.8.2-jdk-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk-21-jdk-slim
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
