FROM maven:3.9.4-eclipse-temurin-21-alpine

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Pre-resolve dependencies (pull everything)
RUN ./mvnw -B dependency:resolve-plugins dependency:resolve

# THEN go offline
RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]
