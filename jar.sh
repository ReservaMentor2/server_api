#!/bin/sh

cd reservamentor-api

mvn package -DskipTests

cd ..

docker build -t giorgio6846/reservamentor-api:latest . 
docker run -t giorgio6846/reservamentor-api