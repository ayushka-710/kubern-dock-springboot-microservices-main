# A. Containerized Services with Kubernetes and Docker (Brewery Microservices):
This project implements microservices, orchestrated for deployment on ARM64 rchitecture via Docker Compose and Kubernetes. This project is based on **microservices-based brewery management system**, integrated with the Elastic Stack for logging and monitoring. This project leverages the following core components of the Elastic Stack: **ElasticSearch, Filebeat, and Kibana**.

1. **ElasticSearch**:
- Elasticsearch is a distributed, open-source search and analytics engine built on Apache Lucene. It's the central data store of the Elastic Stack.

- **Key Function**: It stores data in a way that makes it incredibly fast to **search, aggregate, and analyze** huge volumes of data in near real-time. Think of it as a highly scalable database optimized for search queries.

**Filebeat (Log Collector)**:
- Filebeat is a **lightweight data shipper** that's part of the "Beats" family of agents developed by Elastic.

- **Key Function**: Its primary role is to **monitor specific log files or locations** on your servers (or Docker containers, as in your example), collect new log events, and **forward them efficiently** to Elasticsearch (or to Logstash for further processing).

2. **Kibana**:
- Kibana is an **open-source data visualization and exploration tool** designed to work seamlessly with Elasticsearch.

- **Key Function**: It provides a **user-friendly web interface** to query, analyze, and visualize the data stored in Elasticsearch. It helps you turn raw data into actionable insights through interactive dashboards.

## 1. Use of Elastic Stack:
The Elastic Stack's purpose is to act as a **unified, real-time data analysis platform**. For **observability**, it provides a holistic view of application and infrastructure health by centralizing logs, metrics, and traces, enabling proactive issue detection and rapid troubleshooting. For **SIEM (Security Information and Event Management)**, it collects and correlates security events from across an organization's IT environment, allowing for automated threat detection, anomaly identification, and streamlined incident response to enhance cybersecurity posture.

## 2. Microservices:
### 2.1 MYSQL:
You can assign the desired **MYSQL_ROOT_PASSWORD** and **MYSQL_DATABASE** name within the environment block of the mysql service in your compose-local.yml file.
- MYSQL_ROOT_PASSWORD = < mysql root Password >
- MYSQL_DATABASE = < Database Name >

#### 2.1.1 If MYSQL_ROOT_PASSWORD is changed you need to update the password in every environment section in compose-local.yml
- SPRING_DATASOURCE_PASSWORD = < use the mysql root password >

### 2.2 ElasticSearch:
In the case of Elasticsearch, you can either use the default password or reset the password as per the requirement. To reset the password, you first need to access the Elasticsearch container’s shell. Use the following command to enter the container shell. Once inside, you can execute additional commands required for configuration and password generation.

#### 2.2.1 Full step from accessing the container shell to reset the passowrd
- docker exec -it < container_id > /bin/bash 
- cd /usr/share/elasticsearch
- bin/elasticsearch-reset-password -u elastic

#### 2.2.2 Single step to reset the password (useful in filebeat.yml)
- docker exec -it < container_id > bin/elasticsearch-reset-password -u elastic

(Add the reset password of elastic search in compose-local.yml file within environment variable of elastic search block.)

#### 2.2.3 Generating ElasticSearch serviceAccountToken (useful in kibana.yml):
- docker exec -it < container_id > /bin/bash 
- bin/elasticsearch-service-tokens create elastic kibana my-kibana-token
  - This creates a token for the service account **elastic/kibana**
  - **my-kibana-token** is just a name you give to your token
- bin/elasticsearch-service-tokens list (to check whether the token has been created or not)

(In Elasticsearch 8.x, you are not allowed to connect Kibana (or some other components) using elastic superuser username/password directly.
Kibana (or Fleet, or APM, etc.) uses Service Account Tokens to authenticate securely with Elasticsearch.)

### 2.3 Kibana:
In the case of Kibana,the generated **elasticsearch serviceAccountToken** should be kept in kibana.yml

### 2.4 JMS (ActiveMQ)
In the case of JMS, you can use the username and password as per your requirement.
- ARTEMIS_USERNAME: artemis (default username)
- ARTEMIS_PASSWORD: < Your Password >

#### 2.4.1 Need to update the username and password of JMS broker (ActiveMQ) in **application.properties** 
- sb-services/kbe-brewery-beer-service
- find . -name "application.properties" 2>/dev/null (update the activemq username and password)
    - spring.artemis.user = < username > 
    - spring.artemis.password = < password >

#### 2.4.2 It also contain the username and password to access inventory-service.
- inventory-user = good
- inventory-password = beer

#### 2.4.3 Also need to update it in environment section of compose-local.yml
- SPRING_ARTEMIS_USER: artemis (default username)
- SPRING_ARTEMIS_PASSWORD: < your password >

### 2.5 For other Services You dont need to change anything keep it default (for inventory-service, inventory-failover, beer-service, order-service, gateway)

## 3. To install postman in Red Hat Enterprise Linux 
https://snapcraft.io/install/postman/rhel
(Postman is a tool used to test, develop, and interact with APIs (Application Programming Interfaces). It allows you to send requests to web servers and view their responses.)

- snap run postman (to run postman)

## 4. To view the API endpoint
- cd sb-services/
- find . -name "*Controller*" 2>/dev/null (These will display all of the controller file which consists of API endpoint which can be used in postman.)

## 5. Command to run the compose-local.yml
- docker compose -f compose-local.yml up -d




