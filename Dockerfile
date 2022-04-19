FROM maven:3.6.1-jdk-8-slim as build
WORKDIR /xmon-paste
COPY pom.xml pom.xml
COPY src src
COPY conf conf
RUN mvn package

FROM openjdk:8-jdk-slim
WORKDIR /xmon-paste
COPY --from=build /xmon-paste/target/stork .
EXPOSE 8080
CMD ["bin/xmon-paste", "--run"]
