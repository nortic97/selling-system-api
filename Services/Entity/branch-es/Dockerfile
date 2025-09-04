# Etapa 1: Construcción con Maven
FROM maven:3.9.6-amazoncorretto-21 AS builder

# Definir directorio de trabajo
WORKDIR /app

# Copiar archivos de Maven
COPY pom.xml .
COPY src ./src

# Copiar el JAR de models (precompilado)
COPY libs/models-0.0.1.jar /tmp/models-0.0.1.jar

# Instalar el JAR en el repo local de Maven
RUN mvn install:install-file \
    -Dfile=/tmp/models-0.0.1.jar \
    -DgroupId=com.nortic.pos \
    -DartifactId=models \
    -Dversion=0.0.1 \
    -Dpackaging=jar

# Compilar WAR
RUN mvn clean package -DskipTests

# Etapa 2: Runtime con Amazon Corretto 21
FROM amazoncorretto:21-alpine

# Directorio de trabajo
WORKDIR /app

# Copiar el WAR desde la etapa de compilación
COPY --from=builder /app/target/*.war app.war

# Ejecutar WAR con java
ENTRYPOINT ["java", "-jar", "app.war"]
