# Stage 1: Build the application with Maven and JDK 21
FROM maven:3.8.6-openjdk-18-slim AS build
WORKDIR /home/app
COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:18.0-slim
EXPOSE 8000
COPY --from=build /home/app/target/*.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]


#after creating this file come to this repo and go to cmd and run query
#1. docker build -t rajukr/mmv2-currency-exchange-service:0.0.1-SNAPSHOT .
#eg: docker build -t rajukr/mmv2-${project.artifactId}:${project.version} .
#eg: docker build -t (name should be anything) .
#2. docker image ls
#3. docker container ls
#4. docker ps -a (to see all container which are runing or not)
#5. docker container prune (remove all non running container)
#6. docker rmi (image id)
#7. docker container run -d -p 8000:8000 rajukr/mmv2-currency-exchange-service:0.0.1-SNAPSHOT
#8. docker container stop container id