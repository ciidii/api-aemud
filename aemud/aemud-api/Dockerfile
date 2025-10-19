FROM maven:3.9.6 AS build
WORKDIR /app
ARG SERV_PORT
COPY pom.xml /app
RUN mvn dependency:resolve
COPY . /app
RUN mvn clean
RUN mvn package -DskipTests -X

FROM openjdk:17
COPY --from=build /app/target/*.jar api-aemud.jar
EXPOSE ${SERV_PORT}
CMD ["java","-jar","api-aemud.jar"]