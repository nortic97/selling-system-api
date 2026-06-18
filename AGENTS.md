# Security Architecture Rules

## Authentication Strategy

Authentication and authorization must be centralized.

Use:

* OAuth 2.1
* OpenID Connect (OIDC)
* JWT
* Spring Security

Never implement custom authentication logic inside business services.

---

# Authentication Service

Create a dedicated authentication utility service:

```text
us-auth
```

Responsibilities:

* User authentication
* JWT generation
* JWT validation
* Refresh tokens
* OAuth2 flows
* Role extraction
* Permission extraction
* Token revocation
* Session invalidation

---

# External Authentication Flow

External clients must authenticate via:

* REST APIs
* OpenAPI contracts

Example:

```text
Client
  -> REST
us-auth
  -> JWT
```

---

# Internal Authentication Flow

Internal services must authenticate via:

* gRPC interceptors
* JWT propagation

Example:

```text
ms-checkout
  -> gRPC + JWT
es-users
```

Never expose internal gRPC publicly.

---

# API Gateway Recommendation

Recommended architecture:

```text
Client
   ->
API Gateway
   ->
Microservices
```

The API Gateway should handle:

* Authentication
* Authorization
* Rate limiting
* Request tracing
* Token validation
* CORS
* Routing

Recommended:

* Spring Cloud Gateway

---

# JWT Rules

Use:

* Access Tokens
* Refresh Tokens

Recommended:

* RS256 asymmetric signing

Never use unsigned JWTs.

---

# DTO Rules

All flows must use DTOs.

Never expose entities directly.

---

# DTO Architecture Strategy

Flow:

```text
External Request
    ->
Request DTO
    ->
Mapper
    ->
Internal DTO
    ->
Business Logic
    ->
Response DTO
```

---

# Internal DTO Standardization

The internal DTO must be reusable across the business flow.

Avoid creating duplicated DTOs unnecessarily.

The same internal DTO may travel through:

* services
* use cases
* adapters
* grpc clients
* event publishers

---

# Entity Isolation Rules

Never expose:

* JPA entities
* persistence models
* database structures

outside infrastructure layers.

---

# MapStruct Rules

Use:

* MapStruct

for all mappings.

Never manually map repetitive structures.

---

# MapStruct Requirements

All mappers must use:

```java
@Mapper(componentModel = "spring")
```

Use constructor injection only.

---

# DTO Naming Convention

## Requests

```text
CreateUserRequestDTO
UpdateProductRequestDTO
AuthenticateRequestDTO
```

## Responses

```text
UserResponseDTO
ProductResponseDTO
AuthTokenResponseDTO
```

## Internal

```text
UserDTO
ProductDTO
InventoryDTO
```

---

# DTO Separation Rules

## External DTOs

Used only for:

* REST contracts
* OpenAPI
* external communication

## Internal DTOs

Used for:

* business logic
* grpc communication
* orchestration
* events

---

# Validation Rules

Validation annotations belong only to external DTOs.

Example:

```java
@NotBlank
@Email
@Size
```

Do not place validation annotations inside domain models.

---

# Security Rules

Never expose:

* passwords
* secrets
* tokens
* internal IDs

inside responses.

---

# Password Rules

Passwords must always use:

* BCrypt

Never use:

* SHA1
* MD5
* plain text

---

# Authorization Rules

Authorization must be role-based and permission-based.

Recommended:

```text
ROLE_ADMIN
ROLE_MANAGER
ROLE_SELLER
```

Use granular permissions when necessary.

---

# Service Authorization

Each service must validate JWT internally.

Never trust upstream services blindly.

---

# gRPC Security

All gRPC communications must support:

* JWT propagation
* TLS when required

Use gRPC interceptors for:

* authentication
* tracing
* correlation IDs

---

# Correlation ID Rules

Every request must contain:

```text
X-Correlation-Id
```

This ID must propagate through:

* REST
* gRPC
* Kafka events

---

# Kafka Security Rules

Kafka events must never expose:

* passwords
* secrets
* sensitive personal information

---

# Cache Security Rules

Never cache:

* passwords
* JWTs
* refresh tokens
* sensitive financial information

---

# Recommended Libraries

## Security

* Spring Security
* OAuth2 Resource Server
* Nimbus JOSE JWT

## Mapping

* MapStruct

## Validation

* Jakarta Validation

---

# Final Security Principles

* Authentication centralized
* Authorization decentralized
* DTO-first communication
* No entity exposure
* Internal DTO reuse
* MapStruct mandatory
* JWT everywhere
* gRPC secured
* OAuth2.1 + OIDC standard
* API Gateway as security boundary
* Zero trust between services
