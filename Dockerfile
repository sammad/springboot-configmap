# Base image with Java runtime environment (JDK 17)
FROM eclipse-temurin:17-jdk-jammy

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the local machine to the container
# Replace 'your-app.jar' with the actual name of the JAR file in the target directory
COPY target/springboot-configmap-0.0.1-SNAPSHOT.jar app.jar

# Copy the external config file to /config
COPY external-config/ext-config.yml /external-config/ext-config.yml

# Expose the port the application will run on
EXPOSE 8080

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]