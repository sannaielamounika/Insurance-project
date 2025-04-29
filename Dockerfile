# Use OpenJDK image as base image
FROM openjdk:11-jdk

# Copy the jar file into the container
COPY target/insure-me-1.0.jar /usr/app/insure-me.jar

# Set the working directory
WORKDIR /usr/app

# Expose the port the app will run on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "insure-me.jar"]

