# Etapa 1: Build com Maven
FROM maven:3.9.9-eclipse-temurin-17 AS build

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos de configuração e dependências
COPY pom.xml .
COPY .mvn/ .mvn
COPY mvnw mvnw.cmd ./

# Baixa dependências para cache eficiente
RUN ./mvnw dependency:go-offline

# Copia o código-fonte
COPY src ./src

# Compila o projeto e gera o WAR
RUN ./mvnw clean package -DskipTests

# Etapa 2: Execução com Tomcat
FROM tomcat:10.1-jdk17-corretto

# Remove aplicações padrão do Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia o WAR gerado para o diretório do Tomcat
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Exponha a porta padrão do Tomcat
EXPOSE 8080

# Comando para iniciar o Tomcat
CMD ["catalina.sh", "run"]
