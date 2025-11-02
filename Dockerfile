# Etapa de build com Maven
FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de execução
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/Back-IGesta.jar ./Back-IGesta.jar
ENV PORT=8080
EXPOSE $PORT
CMD ["java", "-jar", "Back-IGesta.jar"]
