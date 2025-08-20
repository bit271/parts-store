# JDK 21, jammy(Ubuntu).
FROM eclipse-temurin:21-jdk-jammy
# Docker work dirrectory.
WORKDIR /app
# Copy the already assembled .jar
COPY target/*.jar partsapp.jar
# Container use port
EXPOSE 8080
# Command that will be executed when the container is started
CMD ["java", "-jar", "partsapp.jar"]

