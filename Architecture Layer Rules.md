# Architecture Layer Rules

## Entity Services (`es-*`)

Entity services MUST use:

* MVC Architecture

They are responsible for:

* CRUD operations
* persistence
* validation
* entity ownership
* REST exposure
* gRPC exposure
* cache access

---

# Entity Service Architecture

Example:

```text id="c1jqg9"
com.nortic.es.user
├── controller
├── service
├── repository
├── entity
├── dto
├── mapper
├── config
├── exception
├── grpc
├── validation
└── util
```

---

# Entity Service Rules

* Use Spring MVC
* Use Spring Data JPA
* Use PostgreSQL
* Use DTO-first architecture
* Use MapStruct
* Use soft delete
* Keep business logic minimal
* No orchestration logic
* No cross-service persistence access

---

# Microservices (`ms-*`)

Microservices MUST use:

* Clean Architecture
* Hexagonal Architecture

---

# Microservice Architecture

Example:

```text id="5j3lzn"
com.nortic.ms.checkout
├── application
├── domain
├── infrastructure
├── entrypoint
├── dto
├── mapper
├── config
├── exception
├── grpc
├── kafka
└── util
```

---

# Microservice Rules

Microservices are responsible for:

* orchestration
* business flows
* saga coordination
* distributed logic
* event publishing
* cross-service communication

They MUST NOT:

* directly access another service database
* own entity persistence from external services
* expose JPA entities

---

# Task Services (`ts-*`)

Task services MUST also use:

* Clean Architecture
* Hexagonal Architecture

---

# Task Service Responsibilities

* scheduled jobs
* async processing
* cache synchronization
* reporting
* notifications
* background workers

---

# Utility Services (`us-*`)

Utility services MUST also use:

* Clean Architecture
* Hexagonal Architecture

---

# Utility Service Responsibilities

* authentication
* storage
* configuration
* notifications
* centralized utilities

---

# Final Architecture Principle

## MVC is ONLY for:

```text id="8h5a7g"
es-*
```

## Hexagonal + Clean Architecture is mandatory for:

```text id="n76drn"
ms-*
ts-*
us-*
```

This separation is intentional to avoid:

* unnecessary complexity in CRUD services
* overengineering
* excessive boilerplate

while still keeping:

* business services scalable
* orchestration decoupled
* infrastructure replaceable
* enterprise maintainability
