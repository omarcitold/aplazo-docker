FROM openjdk:8
EXPOSE 8080
ADD target/aplazo-docker.jar aplazo-docker.jar
ENTRYPOINT ["java","-jar","/aplazo-docker.jar"]