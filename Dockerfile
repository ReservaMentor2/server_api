FROM openjdk:21-jdk-slim

WORKDIR /server

COPY ./reservamentor-api/target/reservamentor-api-0.0.1-SNAPSHOT.jar /server/reservamentor-api.jar
COPY ./reservamentor-api/src/main/resources/application-prod.properties /server/app.properties

# Expone el puerto 8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "reservamentor-api.jar", "--spring.config.location=./app.properties"]
