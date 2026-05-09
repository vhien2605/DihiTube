# DihiTube

## Introduction

DihiTube is a YouTube-like video streaming platform built with a **microservices architecture**.  
The platform provides core functionalities such as:

- Video sharing & streaming
- User authentication & management
- Content search
- Notifications
- Payment processing

---

## Tech Stack

### Backend & Frameworks
- **Java / Spring Boot** — Main backend framework
- **Spring Cloud** — Microservices ecosystem
- **Spring Cloud Gateway** — API Gateway
- **Eureka Server** — Service discovery

### Databases & Storage
- **MySQL** — Identity, Payment, Metadata, Profile services
- **MongoDB** — File metadata & notifications
- **Redis** — Caching layer
- **MinIO** — S3-compatible object storage for video files

### Messaging & Search
- **Apache Kafka** — Event-driven messaging
- **Elasticsearch** — Full-text search engine
- **Kibana** — Elasticsearch visualization

### DevOps
- **Docker Compose** — Container orchestration

## Structure

```text
DihiTube/
├── gateway/                           # API Gateway
├── service-discovery-server/          # Eureka Server
├── identity-service/                  # Authentication & User Management
├── profile-service/                   # User Profile Management
├── metadata-service/                  # Video Metadata Management
├── file-service/                      # Video Upload & File Storage
├── search-service/                    # Elasticsearch Search Service
├── notification-service/              # Notification Service
├── payment-service/                   # Payment Processing
├── docker-compose.yml                 # Infrastructure orchestration
└── README.md
```

---

## Each Microservice Follows DDD Architecture like this

```text
identity-service/
├── src/main/java/dinh/hien/identity_service/
│
│   ├── domain/                        # Core business logic
│   │   ├── user/                      # User aggregate
│   │   │   ├── User.java              # Domain entity
│   │   │   ├── UserId.java            # Value object
│   │   │   ├── UserRepository.java    # Repository interface
│   │   │   └── exception/             # Domain exceptions
│   │   │
│   │   └── authentication/            # Authentication aggregate
│
│   ├── application/                   # Application layer
│   │   ├── usecase/                   # Use cases
│   │   │   ├── auth/                  # Authentication use cases
│   │   │   │   ├── LoginUseCase.java
│   │   │   │   ├── RegisterUseCase.java
│   │   │   │   ├── IntrospectUseCase.java
│   │   │   │   └── RefreshTokenUseCase.java
│   │   │   │
│   │   │   └── user/                  # User use cases
│   │   │
│   │   └── dto/                       # Data transfer objects
│
│   ├── adapter/                       # Driving adapters
│   │   ├── api/                       # REST controllers
│   │   │   ├── AuthController.java
│   │   │   └── UserController.java
│   │   │
│   │   ├── dto/                       # Request/Response DTOs
│   │   │   ├── request/
│   │   │   └── response/
│   │   │
│   │   └── exception/                 # Exception handlers
│
│   └── infra/                         # Driven adapters
│       ├── config/                    # Configuration classes
│       ├── persistence/               # Repository implementations
│       ├── service/                   # External service implementations
│       └── exception/                 # Infrastructure exceptions
│
└── src/main/resources/
    └── application.yml
```

---

## How to Run

### Requirements
- Java 17+
- Maven 3.9+
- Docker
- Docker Compose
---

### 1. Create Docker Network

```bash
docker network create dihi-tube
```

---

### 2. Start Infrastructure Services

```bash
docker-compose up -d
```
### 3. Run Services in local
Run this command inside each microservice:
```bash
mvn clean install
mvn spring-boot:run
```

