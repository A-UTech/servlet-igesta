# Etapa de build com Maven
FROM maven:3.9.3-eclipse-temurin-17 AS build

WORKDIR /app

# Copiar pom e código-fonte
COPY pom.xml .
COPY src ./src

# Build do WAR (sem testes para agilizar)
RUN mvn clean package -DskipTests

# Debug opcional: listar arquivos gerados
RUN ls -l target/

# Etapa de execução: Tomcat
FROM tomcat:10.1-jdk17

# Limpar apps padrão do Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiar WAR gerado como ROOT.war
COPY --from=build /app/target/Back-IGesta-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Expor a porta que o Render fornecerá
ENV PORT 10000
EXPOSE $PORT

# Configurar Tomcat para usar a porta dinâmica do Render
ENV CATALINA_OPTS="-Dport=$PORT"

# Start Tomcat
CMD ["catalina.sh", "run"]
