
# =============================================================================
# Nortic - es-role Service
# =============================================================================

# -----------------------------------------------------------------------------
# Build Stage
# -----------------------------------------------------------------------------
FROM maven:3.9.9-eclipse-temurin-21 AS builder

# Install Protocol Buffers compiler
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
        protobuf-compiler \
        unzip \
        curl \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Maven dependencies (Docker layer cache)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Application source
COPY src ./src

# Build executable JAR
RUN mvn clean package -DskipTests -B \
    && mv target/*.war app.war


# -----------------------------------------------------------------------------
# Runtime Stage
# -----------------------------------------------------------------------------
FROM eclipse-temurin:21-jre-jammy

LABEL org.opencontainers.image.title="es-role" \
      org.opencontainers.image.description="Nortic Selling System - Role Service" \
      org.opencontainers.image.vendor="Nortic Development" \
      org.opencontainers.image.source="https://github.com/nortic97/selling-system-api"

# Non-root user
RUN groupadd -r appuser && \
    useradd -r -g appuser appuser

WORKDIR /app

# Application artifact
COPY --from=builder --chown=appuser:appuser /app/app.war app.war

USER appuser

# Application ports
EXPOSE 8080 9090

# JVM configuration
ENV JAVA_OPTS="-XX:+UseContainerSupport \
    -XX:MaxRAMPercentage=70.0 \
    -XX:InitialRAMPercentage=10.0 \
    -XX:+UseG1GC \
    -Djava.security.egd=file:/dev/./urandom"

ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar app.war"]
