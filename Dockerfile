# Etapa 1: Build do projeto
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Define diretório de trabalho
WORKDIR /app

# Copia o Maven Wrapper e dá permissão de execução
COPY mvnw mvnw.cmd ./
RUN chmod +x mvnw

# Copia o pom.xml (para cache de dependências)
COPY pom.xml ./

# Copia o código-fonte
COPY src ./src

# Compila o projeto e gera o WAR
RUN ./mvnw clean package -DskipTests

# Etapa 2: Imagem final com Tomcat
FROM tomcat:9.0.79-jdk17

# Remove a aplicação default do Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia o WAR gerado na etapa de build
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Expõe porta do Tomcat
EXPOSE 8080

# Comando para rodar o Tomcat
CMD ["catalina.sh", "run"]
