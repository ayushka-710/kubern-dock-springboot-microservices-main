# Containerized Services with Kubernetes and Docker (Brewery Microservices):
This project implements microservices, orchestrated for deployment on ARM64 rchitecture via Docker Compose and Kubernetes. This project is based on **microservices-based brewery management system**, integrated with the Elastic Stack for logging and monitoring. This project leverages the following core components of the Elastic Stack: **ElasticSearch, Filebeat, and Kibana**.

**ElasticSearch**:
- Elasticsearch is a distributed, open-source search and analytics engine built on Apache Lucene. It's the central data store of the Elastic Stack.

- **Key Function**: It stores data in a way that makes it incredibly fast to **search, aggregate, and analyze** huge volumes of data in near real-time. Think of it as a highly scalable database optimized for search queries.

## Microservices:
### MYSQL:
You can assign the desired **MYSQL_ROOT_PASSWORD** and **MYSQL_DATABASE** name within the environment block of the mysql service in your compose-local.yml file.

### ElasticSearch:


