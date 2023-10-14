# base image to use, which is ubuntu:latest. This means that the Docker image will be based
# on the latest version of the Ubuntu operating system.
# uses the `AS` keyword to define a new build stage with the name `builder`.
FROM ubuntu:latest AS builder

# ensure that the latest package information is available
# before installing any new packages or updates.
RUN apt-get update

# installs the `OpenJDK 17 JDK` package using the apt-get package manager.
# the `OpenJDK 17 JDK` package includes the Java Development Kit (JDK)
# required to compile and run Java applications.
RUN apt-get install openjdk-17-jdk -y 


# copies the contents of the current directory (the . symbol) into the Docker image
COPY . .

# installs the Maven build tool using the apt-get package manager.
RUN apt-get install maven -y

# runs the clean and install Maven goals to build and package a Java project.
RUN mvn clean install

# specify the base image for the Docker container
# The `openjdk:17-jdk-slim` image is a pre-built Docker image that includes the
# `OpenJDK 17 JDK` package, which is required to compile and run Java applications. 
# The slim variant of the image is a smaller version of the image that includes
# only the essential components needed to run Java applications.
FROM openjdk:17-jdk-slim

# exposes port 8080 on the Docker container.
EXPOSE 8080

# copies the `jar` file from the builder stage of the Docker build process
# to the app.jar file in the final Docker image.
COPY --from=builder /target/todolist-0.0.1-SNAPSHOT.jar app.jar

# specify the command that should be run when the Docker container is started.
ENTRYPOINT [ "java", "-jar", "app.jar" ]