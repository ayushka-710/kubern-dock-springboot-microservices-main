FROM registry.access.redhat.com/ubi8/ubi:8.5

LABEL MAINTAINER="Ashutosh Kharel"
LABEL BASE_IMAGE="registry.access.redhat.com/ubi8/ubi:8.5"
LABEL JAVA_VERSION="11"

# Install OpenJDK 11, curl, bash, and required build tools for redis-cli
RUN dnf install -y --nodocs java-11-openjdk-headless bash curl gcc make wget tar && \
    dnf clean all && \
    # Download and build redis-cli only
    wget http://download.redis.io/releases/redis-7.4.2.tar.gz && \
    tar xzf redis-7.4.2.tar.gz && \
    cd redis-7.4.2 && \
    make redis-cli && \
    mv src/redis-cli /usr/local/bin/ && \
    cd .. && rm -rf redis-7.4.2*

# Set working directory
WORKDIR /work/

# Copy application JAR
COPY target/*.jar /work/application.jar

# Copy Datadog Java Agent
# COPY dd-java-agent.jar /work/dd-java-agent.jar

# Expose application port
EXPOSE 8080

# Set environment variables for Datadog APM
# ENV DD_SERVICE="hello-world-java"
# ENV DD_ENV="staging"
# ENV DD_VERSION="1.0-SNAPSHOT"
# ENV DD_AGENT_HOST="localhost"  
# ENV JAVA_OPTS="-javaagent:/work/dd-java-agent.jar -Xmx1024M"
# ENV LOG_FOLDER="/work/log"

# Set default shell to bash
SHELL ["/bin/bash", "-c"]

# Run the Java application with Datadog tracing enabled
#CMD ["java", "-javaagent:/work/dd-java-agent.jar", "-Ddd.profiling.enabled=true", "-Ddd.logs.injection=true", "-Ddd.service=hello-world-java", "-Ddd.env=staging", "-Ddd.version=1.0", "-jar", "/work/application.jar"]

CMD ["java", "-jar", "/work/application.jar"]
