FROM registry.access.redhat.com/ubi8/ubi-minimal:8.5

LABEL MAINTAINER="Muhammad Edwin < edwin at redhat dot com >"
LABEL BASE_IMAGE="registry.access.redhat.com/ubi8/ubi-minimal:8.5"
LABEL JAVA_VERSION="11"

# Install OpenJDK 11 and bash
RUN microdnf install --nodocs java-11-openjdk-headless bash && microdnf clean all

# Set working directory
WORKDIR /work/

# Copy application JAR
COPY target/*.jar /work/application.jar

# Copy Datadog Java Agent
COPY dd-java-agent.jar /work/dd-java-agent.jar

# Expose application port
EXPOSE 8080

# Set environment variables for Datadog APM
ENV DD_SERVICE="hello-world-java"
ENV DD_ENV="staging"
ENV DD_VERSION="1.0-SNAPSHOT"
ENV DD_AGENT_HOST="localhost"  
ENV JAVA_OPTS="-javaagent:/work/dd-java-agent.jar -Xmx1024M"
ENV LOG_FOLDER="/work/log"

# Set default shell to bash
SHELL ["/bin/bash", "-c"]

# Run the Java application with Datadog tracing enabled
CMD ["java", "-javaagent:/work/dd-java-agent.jar", "-Ddd.profiling.enabled=true", "-Ddd.logs.injection=true", "-Ddd.service=hello-world-java", "-Ddd.env=staging", "-Ddd.version=1.0", "-jar", "/work/application.jar"]

