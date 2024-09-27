FROM eclipse-temurin:21-jdk-alpine

WORKDIR /server

COPY ./reservamentor-api /server/

RUN ./mvnw dependency:go-offline

CMD [ "./mvnw", "spring-boot:run", "-Dspring.run.profiles=PROD" ]