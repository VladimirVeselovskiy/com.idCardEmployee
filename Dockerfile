FROM openjdk:11.0.16-jdk-slim as builder
WORKDIR /app
COPY . /app/.
CMD mvn clean package -DskipTests

FROM openjdk:11.0.16-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/*.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/*.jar"]
