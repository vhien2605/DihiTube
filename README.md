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
│   ├── IdentityServiceApplication.java
│
│   ├── domain/                                      # Domain Layer
│   │
│   │   ├── exception/
│   │   │   ├── DError.java
│   │   │   └── DomainException.java
│   │   │
│   │   ├── role/
│   │   │   ├── IRoleRepository.java
│   │   │   ├── Role.java
│   │   │   └── RoleId.java
│   │   │
│   │   └── user/
│   │       ├── Email.java
│   │       ├── IUserRepository.java
│   │       ├── Password.java
│   │       ├── PasswordHasher.java
│   │       ├── User.java
│   │       ├── UserId.java
│   │       └── event/
│   │           └── UserCreatedEvent.java
│
│   ├── application/                                 # Application Layer
│   │
│   │   ├── external/
│   │   │   └── publisher/
│   │   │       └── UserEventPublisher.java
│   │   │
│   │   ├── service/
│   │   │   └── token/
│   │   │       ├── ITokenRepository.java
│   │   │       ├── ITokenService.java
│   │   │       ├── TokenPayload.java
│   │   │       ├── TokenProperties.java
│   │   │       └── TokenType.java
│   │   │
│   │   └── usecase/
│   │       ├── introspect/
│   │       │   ├── IntrospectCommand.java
│   │       │   ├── IntrospectResult.java
│   │       │   └── IntrospectUseCase.java
│   │       │
│   │       ├── login/
│   │       │   ├── LoginCommand.java
│   │       │   ├── LoginResult.java
│   │       │   └── UserLoginUseCase.java
│   │       │
│   │       ├── logout/
│   │       │   ├── LogoutCommand.java
│   │       │   └── LogoutUseCase.java
│   │       │
│   │       ├── password/
│   │       │   ├── ChangePasswordCommand.java
│   │       │   └── ChangePasswordUseCase.java
│   │       │
│   │       ├── refresh/
│   │       │   ├── RefreshCommand.java
│   │       │   ├── RefreshResult.java
│   │       │   └── UserTokenRefreshUseCase.java
│   │       │
│   │       └── register/
│   │           ├── FailCommand.java
│   │           ├── ProfileCreatedFailUseCase.java
│   │           ├── RegisterCommand.java
│   │           ├── RegisterResult.java
│   │           └── UserRegisterUseCase.java
│
│   ├── adapter/                                     # Adapter Layer
│   │
│   │   ├── api/
│   │   │   └── AuthController.java
│   │   │
│   │   ├── dto/
│   │   │   ├── request/
│   │   │   │   ├── ChangePasswordRequestDTO.java
│   │   │   │   ├── IntrospectRequestDTO.java
│   │   │   │   ├── LoginRequestDTO.java
│   │   │   │   ├── RefreshRequestDTO.java
│   │   │   │   └── RegisterRequestDTO.java
│   │   │   │
│   │   │   └── response/
│   │   │       ├── ApiErrorResponse.java
│   │   │       ├── ApiResponse.java
│   │   │       ├── ApiSuccessResponse.java
│   │   │       └── auth/
│   │   │           ├── IntrospectResponseDTO.java
│   │   │           ├── JwtResponseDTO.java
│   │   │           └── RefreshResponseDTO.java
│   │   │
│   │   ├── exception/
│   │   │   └── GlobalExceptionHandler.java
│   │   │
│   │   ├── mapper/
│   │   │   └── AuthMapper.java
│   │   │
│   │   └── messaging/
│   │       ├── event/
│   │       │   └── UserProfileCreatedFailEvent.java
│   │       └── listener/
│   │           └── UserKafkaListener.java
│
│   └── infra/                                       # Infrastructure Layer
│       │
│       ├── config/
│       │   ├── CustomJwtDecoder.java
│       │   ├── SecurityConfig.java
│       │   └── authHandlers/
│       │       ├── CustomAccessDeniedHandler.java
│       │       └── CustomAuthenticationEntryPoint.java
│       │
│       ├── exception/
│       │   ├── AuthException.java
│       │   ├── InfraError.java
│       │   └── InfraException.java
│       │
│       ├── external/
│       │   └── messaging/
│       │       ├── event/
│       │       │   └── KafkaUserCreatedEvent.java
│       │       └── publisher/
│       │           └── UserEventPublisherImpl.java
│       │
│       ├── mapper/
│       │   ├── RoleMapper.java
│       │   ├── UserMapper.java
│       │   └── event/
│       │       └── UserEventMapper.java
│       │
│       ├── model/
│       │   ├── AccessToken.java
│       │   ├── JpaRole.java
│       │   ├── JpaUser.java
│       │   └── RefreshToken.java
│       │
│       ├── persistence/
│       │   ├── impl/
│       │   │   ├── RoleRepositoryImpl.java
│       │   │   ├── TokenRepositoryImpl.java
│       │   │   └── UserRepositoryImpl.java
│       │   │
│       │   └── repository/
│       │       ├── JpaRefreshTokenRepository.java
│       │       ├── RedisAccessRepository.java
│       │       ├── RoleJpaRepository.java
│       │       └── UserJpaRepository.java
│       │
│       └── service/
│           ├── PasswordHasherImpl.java
│           └── TokenServiceImpl.java
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

