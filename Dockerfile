FROM maven:3.9.11-amazoncorretto-21-alpine as builder
WORKDIR /builder
COPY . .
RUN mvn clean package

FROM amazoncorretto:21-alpine
WORKDIR /app
COPY --from=builder /builder/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]