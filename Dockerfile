FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /usr/local/app
COPY ./ /usr/local/app/

FROM amazoncorretto:17-alpine

RUN apk update

RUN mkdir /opt/app

COPY --from=build /usr/local/app/target/*.jar /opt/app/app.jar

ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]

