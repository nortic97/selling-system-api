# Selling System API

Documentación completa del proyecto Selling System API, incluyendo reglas de negocio, estructura de paquetes, arquitectura, configuración y despliegue.

## Índice

- [Nomenclatura de Servicios](#nomenclatura-de-servicios)
- [Estructura de Paquetes](#estructura-de-paquetes)
- [Arquitectura](#arquitectura)
- [Service Discovery con Eureka](#service-discovery-con-eureka)
- [Inicialización de Proyectos](#inicialización-de-proyectos)
- [Versiones y Dependencias](#versiones-y-dependencias)
- [Comandos de Ejecución](#comandos-de-ejecución)
- [Configuración Docker](#configuración-docker)
- [Monitoreo y Logs](#monitoreo-y-logs)

## Nomenclatura de Servicios

Los servicios siguen una convención de nombres basada en su tipo:

### Tipos de Servicios

- **es** (Entity Service): Servicios que gestionan entidades del dominio con arquitectura MVC
  - Ejemplos: `es-branch`, `es-feature`, `es-permission`, `es-plan`, `es-role`, `es-store`, `es-user`

- **us** (Utility Service): Servicios de utilidad y soporte
  - Ejemplos: `us-eureka-server`

- **ms** (Microservice): Microservicios de negocio con arquitectura hexagonal/clean
  - Pendiente de implementación

- **ts** (Task Service): Servicios de tareas y procesos asíncronos
  - Pendiente de implementación

### Formato de Nombres

```
<tipo de servicio>-<nombre del servicio>
```

Ejemplos:
- `es-user` - Entity Service para usuarios
- `us-eureka-server` - Utility Service para Eureka
- `ms-order` - Microservice para órdenes (futuro)
- `ms-notification` - Task Service para notificaciones (futuro)

## Estructura de Paquetes

### Paquetes Java

Todos los paquetes siguen la estructura:

```
com.nortic.pos.<tipo de servicio>.<nombre del servicio>
```

Ejemplos:
- Entity Service: `com.nortic.pos.es.branch`
- Entity Service: `com.nortic.pos.es.user`
- Utility Service: `com.nortic.pos.us.eureka-server`

### Estructura de Directorios

```
selling-system-api/
├── Services/
│   ├── Entity/           # Entity Services (MVC)
│   │   ├── es-branch/
│   │   ├── es-feature/
│   │   ├── es-permission/
│   │   ├── es-plan/
│   │   ├── es-role/
│   │   ├── es-store/
│   │   └── es-user/
│   ├── Utility/          # Utility Services
│   │   └── us-eureka-server/
│   ├── Microservice/     # Microservices (Hexagonal/Clean)
│   └── Task/             # Task Services
└── logs/                 # Directorio para logs
```

### Estructura Interna de Entity Services (MVC)

```
es-<service>/
├── src/main/java/com/nortic/pos/es/<service>/
│   ├── config/           # Configuraciones (Security, etc.)
│   ├── controller/      # Controladores REST
│   ├── exception/        # Excepciones y manejo global
│   ├── grpc/            # Controladores gRPC
│   │   ├── exception/
│   │   └── interceptor/
│   ├── mapper/          # Mappers (Entity, Rest, Grpc)
│   ├── model/
│   │   ├── dto/         # Data Transfer Objects
│   │   └── entity/      # Entidades JPA
│   ├── repository/      # Repositorios JPA
│   ├── security/         # Configuraciones de seguridad
│   ├── service/         # Lógica de negocio
│   └── util/            # Utilidades
├── src/main/resources/
│   ├── OpenApi.yaml     # Especificación OpenAPI
│   ├── application.yaml # Configuración de la aplicación
│   ├── logback-spring.xml # Configuración de logs
│   ├── schema.sql       # Script de inicialización de BD
│   └── banner.txt
└── pom.xml
```

## Arquitectura

### Entity Services (MVC)

Los Entity Services utilizan arquitectura **Model-View-Controller (MVC)** tradicional:

- **Model**: Entidades JPA (`model/entity/`) y DTOs (`model/dto/`)
- **View**: Respuestas REST y gRPC
- **Controller**: Controladores REST (`controller/`) y gRPC (`grpc/`)

Características:
- Spring Boot MVC con JPA/Hibernate
- Exposición de APIs REST y gRPC
- Validaciones con Bean Validation
- Seguridad con Spring Security + JWT

### Otros Servicios (Hexagonal/Clean)

Los Microservices y Task Services utilizarán **Arquitectura Hexagonal** y **Clean Architecture**:

- **Domain Layer**: Entidades del dominio puro, sin dependencias externas
- **Application Layer**: Casos de uso y puertos de entrada/salida
- **Infrastructure Layer**: Implementaciones de puertos (BD, APIs externas, etc.)
- **Interface Layer**: Controladores, adapters

Características:
- Separación clara de dominio e infraestructura
- Inyección de dependencias basada en puertos
- Fácil testabilidad
- Independencia de frameworks

## Service Discovery con Eureka

El proyecto utiliza **Netflix Eureka** para service discovery.

### Configuración del Servidor Eureka

**Archivo**: `Services/Utility/us-eureka-server/src/main/resources/application.yaml`

```yaml
spring:
  application:
    name: us-eureka-server

server:
  port: 8761

eureka:
  instance:
    hostname: eureka
  client:
    register-with-eureka: false
    fetch-registry: false
```

### Configuración del Cliente Eureka

**Archivo**: `Services/Entity/es-<service>/src/main/resources/application.yaml`

```yaml
eureka:
  client:
    service-url:
      defaultZone: ${EUREKA_URL:http://localhost:8761/eureka}
    registry-fetch-interval-seconds: 5
  instance:
    prefer-ip-address: true
    metadata-map:
      metrics-path: /actuator/prometheus
```

### Anotación en la Aplicación

```java
@SpringBootApplication
@EnableDiscoveryClient
public class EsBranchApplication {
    public static void main(String[] args) {
        SpringApplication.run(EsBranchApplication.class, args);
    }
}
```

## Inicialización de Proyectos

### Plantilla de POM para Entity Services

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>4.1.0</version>
        <relativePath/>
    </parent>
    
    <groupId>com.nortic.pos.es</groupId>
    <artifactId><nombre-servicio></artifactId>
    <version>0.0.1</version>
    <packaging>war</packaging>
    <name>es-<nombre-servicio></name>
    <description>es-<nombre-servicio></description>
    
    <properties>
        <java.version>21</java.version>
        <org.mapstruct.version>1.6.3</org.mapstruct.version>
        <org.slf4j.version>2.0.18</org.slf4j.version>
        <io.swagger.core.v3.version>2.2.49</io.swagger.core.v3.version>
        <org.openapitools.version>7.23.0</org.openapitools.version>
        <io.jsonwebtoken.version>0.13.0</io.jsonwebtoken.version>
        <spring-cloud.version>2025.1.3</spring-cloud.version>
    </properties>
</project>
```

### Plantilla de POM para Utility Services

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>4.1.1</version>
        <relativePath/>
    </parent>
    
    <groupId>com.nortic.pos</groupId>
    <artifactId>us-<nombre-servicio></artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>war</packaging>
    <name>us-<nombre-servicio></name>
    <description>us-<nombre-servicio></description>
    
    <properties>
        <java.version>21</java.version>
        <spring-cloud.version>2025.1.3</spring-cloud.version>
    </properties>
</project>
```

## Versiones y Dependencias

### Versiones Principales

- **Java**: 21
- **Spring Boot**: 4.1.0 (Entity Services), 4.1.1 (Utility Services)
- **Spring Cloud**: 2025.1.3
- **PostgreSQL**: Driver incluido en runtime
- **Maven**: 3.9.9+

### Dependencias Clave

| Dependencia | Versión | Uso |
|-------------|---------|-----|
| MapStruct | 1.6.3 | Mapeo de objetos |
| SLF4J | 2.0.18 | Logging |
| Swagger Core | 2.2.49 | Documentación OpenAPI |
| OpenAPI Tools | 7.23.0 | Generación de código |
| JWT (jjwt) | 0.13.0 | Autenticación JWT |
| Micrometer Prometheus | - | Métricas |
| Spring Cloud Eureka | 2025.1.3 | Service Discovery |

### Dependencias de Spring Boot

- `spring-boot-starter-webmvc` - REST APIs
- `spring-boot-starter-data-jpa` - JPA/Hibernate
- `spring-boot-starter-grpc-server` - gRPC
- `spring-boot-starter-security` - Seguridad
- `spring-boot-starter-validation` - Validaciones
- `spring-boot-starter-actuator` - Métricas y health checks
- `spring-cloud-starter-netflix-eureka-client` - Eureka client

## Comandos de Ejecución

### Compilar el Proyecto

```bash
mvn clean compile
```

### Ejecutar Tests

```bash
mvn test
```

### Empaquetar (WAR)

```bash
mvn clean package
```

### Empaquetar sin Tests

```bash
mvn clean package -DskipTests
```

### Ejecutar la Aplicación Localmente

```bash
mvn spring-boot:run
```

### Ejecutar con Perfil Específico

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Generar Código OpenAPI

El código OpenAPI se genera automáticamente durante la fase `compile`:

```bash
mvn compile
```

### Generar Código gRPC

El código gRPC se genera automáticamente durante la fase `compile`:

```bash
mvn compile
```

### Variables de Entorno

Las aplicaciones se configuran mediante variables de entorno:

```bash
# Configuración General
export APPLICATION_NAME=es-branch
export ENVIRONMENT=local

# Base de Datos
export DB_HOST=localhost
export DB_PORT=5432
export DB_NAME=selling_system
export DB_USERNAME=postgres
export DB_PASSWORD=123456

# Servidor
export PORT=8080
export SERVER_ADDRESS=0.0.0.0

# gRPC
export GRPC_PORT=9091

# JWT
export JWT_SECRET=your-secret-key

# Eureka
export EUREKA_URL=http://localhost:8761/eureka

# JPA
export JPA_DDL_AUTO=validate
export SQL_INIT_MODE=always
```

## Configuración Docker

### Dockerfile para Entity Services

Cada servicio debe tener un `Dockerfile` en su raíz:

```dockerfile
# Build stage
FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/target/*.war app.war
EXPOSE 8080
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "app.war"]
```

### Construir Imagen Docker

```bash
docker build -t nortic/es-branch:0.0.1 .
```

### Ejecutar Contenedor

```bash
docker run -p 8080:8080 -p 9090:9090 \
  -e DB_HOST=postgres \
  -e DB_PORT=5432 \
  -e DB_NAME=selling_system \
  -e DB_USERNAME=postgres \
  -e DB_PASSWORD=123456 \
  -e EUREKA_URL=http://us-eureka-server:8761/eureka \
  nortic/es-branch:0.0.1
```

### Docker Compose (Ejemplo)

```yaml
version: '3.8'

services:
  postgres:
    image: postgres:16
    environment:
      POSTGRES_DB: selling_system
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: 123456
    ports:
      - "5432:5432"

  us-eureka-server:
    build: ./Services/Utility/us-eureka-server
    ports:
      - "8761:8761"

  es-branch:
    build: ./Services/Entity/es-branch
    ports:
      - "8080:8080"
      - "9091:9091"
    environment:
      - DB_HOST=postgres
      - DB_PORT=5432
      - DB_NAME=selling_system
      - DB_USERNAME=postgres
      - DB_PASSWORD=123456
      - EUREKA_URL=http://us-eureka-server:8761/eureka
    depends_on:
      - postgres
      - us-eureka-server
```

## Monitoreo y Logs

### Configuración de Logs (Logback)

**Archivo**: `src/main/resources/logback-spring.xml`

La configuración de logs incluye:

- **Console Appender**: Salida colorida para desarrollo
- **File Appender**: Archivos de logs para producción (sin colores)
- **Rolling Policy**: Rotación por tamaño (50MB) y tiempo (7 días)
- **Total Size Cap**: Máximo 1GB de logs archivados

**Ubicación de Logs**:
```
logs/<application-name>/<application-name>.log
logs/<application-name>/archive/<application-name>.YYYY-MM-DD.i.log.gz
```

### Métricas con Prometheus

Los servicios exponen métricas en el endpoint:

```
http://localhost:8080/actuator/prometheus
```

**Configuración en application.yaml**:

```yaml
management:
  endpoints:
    web:
      exposure:
        include:
          - prometheus
          - health
          - info
  endpoint:
    health:
      show-details: always
```

### Integración con Grafana Alloy y Loki

#### Loki (Log Aggregation)

Loki consume los logs de los archivos generados por Logback. La configuración de Alloy debe apuntar al directorio `logs/`:

```yaml
// Configuración de ejemplo para Alloy
loki.write {
  endpoint {
    url = "http://loki:3100/loki/api/v1/push"
  }
}

local.file_match {
  path_targets = [{
    __path__ = "/app/logs/**/*.log"
    labels = {
      service = "${applicationName}"
      env = "${env}"
    }
  }]
}
```

#### Grafana (Visualización)

Grafana se configura para consumir:

1. **Prometheus**: Métricas de los servicios
2. **Loki**: Logs centralizados

**Dashboards recomendados**:
- JVM Micrometer
- Spring Boot Statistics
- Custom service dashboards

#### Health Checks

Cada servicio expone health checks:

```
http://localhost:8080/actuator/health
```

Eureka registra la ruta de métricas en sus metadatos para descubrimiento automático.

## Reglas de Negocio Adicionales

### Validaciones en OpenAPI

Para los Entity Services, las validaciones de Spring Boot se agregan usando la propiedad `x-field-extra-annotation` en el contrato OpenAPI:

Ejemplos:
- `storeId`: `x-field-extra-annotation: '@NotNull'`
- `name`: `x-field-extra-annotation: '@NotBlank @Size(min = 3, max = 50)'`
- `description`: `x-field-extra-annotation: '@Size(max = 255)'`
- `isActive`: `x-field-extra-annotation: '@NotNull'`

Este patrón debe aplicarse consistentemente en todos los Entity Services (es-store, es-user, etc.).

### Seguridad

- Autenticación mediante JWT
- Configuración de seguridad en `config/SecurityFilterConfig.java`
- Interceptors gRPC en `grpc/interceptor/GrpcInterceptor.java`
- Context propagation en `grpc/interceptor/ContextKeys.java`

### Base de Datos

- Motor: PostgreSQL
- Inicialización: `schema.sql` en resources
- JPA DDL: `validate` en producción, `update/create` en desarrollo
- Timezone: `America/Bogota` (configurable)

## Próximos Pasos

- [ ] Implementar primer Microservice con arquitectura hexagonal
- [ ] Implementar primer Task Service
- [ ] Configurar docker-compose completo
- [ ] Configurar Grafana dashboards
- [ ] Configurar CI/CD pipeline