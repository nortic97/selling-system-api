# ENTITY-TABLES.md

# Enterprise Selling System - Complete Entity Tables

## General Rules

All entity tables:

- Belong to an es-* service
- Use soft delete
- Use UUID as primary key
- Are the source of truth
- Expose REST externally
- Expose gRPC internally
- Must not be accessed directly by other services

# PostgreSQL Standards

## UUID

UUID PRIMARY KEY DEFAULT gen_random_uuid()

# Audit Columns

All tables must contain:

created_at TIMESTAMP WITH TIME ZONE NOT NULL,  
updated_at TIMESTAMP WITH TIME ZONE NOT NULL,  
deleted_at TIMESTAMP WITH TIME ZONE NULL

# Soft Delete

Delete operations must never physically delete records.

Instead:

deleted_at = CURRENT_TIMESTAMP

# ENTITY SERVICES

| **Service** | **Tables** |
| ------------- | ------------------------------------------------------------- |
| es-user       | user, role, user_role, user_workspace                         |
| ---           | ---                                                           |
| es-store      | store                                                         |
| ---           | ---                                                           |
| es-branch     | branch                                                        |
| ---           | ---                                                           |
| es-product    | product, brand, category, product_category, product_asset     |
| ---           | ---                                                           |
| es-customer   | customer                                                      |
| ---           | ---                                                           |
| es-supplier   | supplier                                                      |
| ---           | ---                                                           |
| es-plan       | plan, user_plan_payment                                       |
| ---           | ---                                                           |

# store

Service:

es-store

| **Field** | **Type** | **Nullable** |
| -------------- | ------------------------ | ------------ |
| id             | UUID                     | No           |
| owner_id       | UUID                     | No           |
| tax_id         | VARCHAR(100)             | No           |
| name           | VARCHAR(255)             | No           |
| logo           | TEXT                     | Yes          |
| description    | TEXT                     | Yes          |
| slogan         | VARCHAR(255)             | Yes          |
| address        | VARCHAR(255)             | Yes          |
| country        | VARCHAR(100)             | Yes          |
| state_province | VARCHAR(100)             | Yes          |
| city           | VARCHAR(100)             | Yes          |
| phone_number   | VARCHAR(50)              | Yes          |
| email          | VARCHAR(255)             | Yes          |
| is_active      | BOOLEAN                  | No           |
| created_at     | TIMESTAMP WITH TIME ZONE | No           |
| updated_at     | TIMESTAMP WITH TIME ZONE | No           |
| deleted_at     | TIMESTAMP WITH TIME ZONE | Yes          |

# branch

Service:

es-branch

| **Field** | **Type** | **Nullable** |
| ------------ | ------------------------ | ------------ |
| id           | UUID                     | No           |
| store_id     | UUID                     | No           |
| name         | VARCHAR(255)             | No           |
| address      | VARCHAR(255)             | Yes          |
| phone_number | VARCHAR(50)              | Yes          |
| email        | VARCHAR(255)             | Yes          |
| is_active    | BOOLEAN                  | No           |
| created_at   | TIMESTAMP WITH TIME ZONE | No           |
| updated_at   | TIMESTAMP WITH TIME ZONE | No           |
| deleted_at   | TIMESTAMP WITH TIME ZONE | Yes          |

# user

Service:

es-user

| **Field** | **Type** | **Nullable** |
| --------------- | ------------------------ | ------------ |
| id              | UUID                     | No           |
| first_name      | VARCHAR(100)             | No           |
| last_name       | VARCHAR(100)             | No           |
| document_type   | VARCHAR(50)              | No           |
| document_number | VARCHAR(100)             | No           |
| username        | VARCHAR(100)             | No           |
| password        | VARCHAR(255)             | No           |
| email           | VARCHAR(255)             | No           |
| phone           | VARCHAR(50)              | Yes          |
| is_active       | BOOLEAN                  | No           |
| address         | VARCHAR(255)             | Yes          |
| date_of_birth   | DATE                     | Yes          |
| photo           | TEXT                     | Yes          |
| created_at      | TIMESTAMP WITH TIME ZONE | No           |
| updated_at      | TIMESTAMP WITH TIME ZONE | No           |
| deleted_at      | TIMESTAMP WITH TIME ZONE | Yes          |

# role

Service:

es-user

| **Field** | **Type** | **Nullable** |
| ----------- | ------------------------ | ------------ |
| id          | UUID                     | No           |
| store_id    | UUID                     | No           |
| name        | VARCHAR(100)             | No           |
| description | TEXT                     | Yes          |
| is_active   | BOOLEAN                  | No           |
| created_at  | TIMESTAMP WITH TIME ZONE | No           |
| updated_at  | TIMESTAMP WITH TIME ZONE | No           |
| deleted_at  | TIMESTAMP WITH TIME ZONE | Yes          |

# user_role

Service:

es-user

| **Field** | **Type** | **Nullable** |
| --------- | -------- | ------------ |
| user_id   | UUID     | No           |
| role_id   | UUID     | No           |

# user_workspace

Service:

es-user

| **Field** | **Type** | **Nullable** |
| ---------- | ------------------------ | ------------ |
| id         | UUID                     | No           |
| user_id    | UUID                     | No           |
| store_id   | UUID                     | No           |
| branch_id  | UUID                     | Yes          |
| is_owner   | BOOLEAN                  | No           |
| created_at | TIMESTAMP WITH TIME ZONE | No           |
| updated_at | TIMESTAMP WITH TIME ZONE | No           |
| deleted_at | TIMESTAMP WITH TIME ZONE | Yes          |

# brand

Service:

es-product

| **Field** | **Type** | **Nullable** |
| ----------- | ------------------------ | ------------ |
| id          | UUID                     | No           |
| name        | VARCHAR(255)             | No           |
| description | TEXT                     | Yes          |
| logo        | TEXT                     | Yes          |
| created_at  | TIMESTAMP WITH TIME ZONE | No           |
| updated_at  | TIMESTAMP WITH TIME ZONE | No           |
| deleted_at  | TIMESTAMP WITH TIME ZONE | Yes          |

# category

Service:

es-product

| **Field** | **Type** | **Nullable** |
| ----------- | ------------------------ | ------------ |
| id          | UUID                     | No           |
| store_id    | UUID                     | No           |
| parent_id   | UUID                     | Yes          |
| name        | VARCHAR(255)             | No           |
| description | TEXT                     | Yes          |
| created_at  | TIMESTAMP WITH TIME ZONE | No           |
| updated_at  | TIMESTAMP WITH TIME ZONE | No           |
| deleted_at  | TIMESTAMP WITH TIME ZONE | Yes          |

# product

Service:

es-product

| **Field** | **Type** | **Nullable** |
| ----------- | ------------------------ | ------------ |
| id          | UUID                     | No           |
| store_id    | UUID                     | No           |
| name        | VARCHAR(255)             | No           |
| description | TEXT                     | Yes          |
| sku         | VARCHAR(100)             | No           |
| barcode     | VARCHAR(100)             | Yes          |
| brand_id    | UUID                     | Yes          |
| status      | VARCHAR(50)              | No           |
| created_at  | TIMESTAMP WITH TIME ZONE | No           |
| updated_at  | TIMESTAMP WITH TIME ZONE | No           |
| deleted_at  | TIMESTAMP WITH TIME ZONE | Yes          |

# product_category

Service:

es-product

| **Field** | **Type** | **Nullable** |
| ----------- | -------- | ------------ |
| product_id  | UUID     | No           |
| category_id | UUID     | No           |

# product_asset

Service:

es-product

| **Field** | **Type** | **Nullable** |
| ----------- | ------------------------ | ------------ |
| id          | UUID                     | No           |
| product_id  | UUID                     | No           |
| url         | TEXT                     | No           |
| type        | VARCHAR(50)              | No           |
| order_index | INTEGER                  | No           |
| created_at  | TIMESTAMP WITH TIME ZONE | No           |
| updated_at  | TIMESTAMP WITH TIME ZONE | No           |
| deleted_at  | TIMESTAMP WITH TIME ZONE | Yes          |

# customer

Service:

es-customer

| **Field** | **Type** | **Nullable** |
| --------------- | ------------------------ | ------------ |
| id              | UUID                     | No           |
| first_name      | VARCHAR(100)             | No           |
| last_name       | VARCHAR(100)             | No           |
| document_type   | VARCHAR(50)              | No           |
| document_number | VARCHAR(100)             | No           |
| email           | VARCHAR(255)             | Yes          |
| phone           | VARCHAR(50)              | Yes          |
| address         | VARCHAR(255)             | Yes          |
| created_at      | TIMESTAMP WITH TIME ZONE | No           |
| updated_at      | TIMESTAMP WITH TIME ZONE | No           |
| deleted_at      | TIMESTAMP WITH TIME ZONE | Yes          |

# supplier

Service:

es-supplier

| **Field** | **Type** | **Nullable** |
| -------------- | ------------------------ | ------------ |
| id             | UUID                     | No           |
| store_id       | UUID                     | No           |
| branch_id      | UUID                     | Yes          |
| company_name   | VARCHAR(255)             | No           |
| tax_id         | VARCHAR(100)             | No           |
| name           | VARCHAR(255)             | Yes          |
| email          | VARCHAR(255)             | Yes          |
| phone          | VARCHAR(50)              | Yes          |
| address        | VARCHAR(255)             | Yes          |
| state_province | VARCHAR(100)             | Yes          |
| city           | VARCHAR(100)             | Yes          |
| country        | VARCHAR(100)             | Yes          |
| created_at     | TIMESTAMP WITH TIME ZONE | No           |
| updated_at     | TIMESTAMP WITH TIME ZONE | No           |
| deleted_at     | TIMESTAMP WITH TIME ZONE | Yes          |

# plan

Service:

es-plan

| **Field** | **Type** | **Nullable** |
| ------------ | ------------------------ | ------------ |
| id           | UUID                     | No           |
| name         | VARCHAR(255)             | No           |
| description  | TEXT                     | Yes          |
| price        | NUMERIC(19,4)            | No           |
| type_of_plan | VARCHAR(100)             | No           |
| created_at   | TIMESTAMP WITH TIME ZONE | No           |
| updated_at   | TIMESTAMP WITH TIME ZONE | No           |
| deleted_at   | TIMESTAMP WITH TIME ZONE | Yes          |

# user_plan_payment

Service:

es-plan

| **Field** | **Type** | **Nullable** |
| ---------------- | ------------------------ | ------------ |
| id               | UUID                     | No           |
| user_id          | UUID                     | No           |
| plan_id          | UUID                     | No           |
| transaction_id   | VARCHAR(255)             | No           |
| payment_provider | VARCHAR(100)             | No           |
| status           | VARCHAR(50)              | No           |
| currency         | VARCHAR(10)              | No           |
| invoice_number   | VARCHAR(100)             | Yes          |
| start_date       | TIMESTAMP WITH TIME ZONE | No           |
| end_date         | TIMESTAMP WITH TIME ZONE | Yes          |
| amount_paid      | NUMERIC(19,4)            | No           |
| is_active        | BOOLEAN                  | No           |
| paid_at          | TIMESTAMP WITH TIME ZONE | Yes          |
| created_at       | TIMESTAMP WITH TIME ZONE | No           |
| updated_at       | TIMESTAMP WITH TIME ZONE | No           |
| deleted_at       | TIMESTAMP WITH TIME ZONE | Yes          |

# Future Business Services

The following services will orchestrate logic only:

ms-checkout  
ms-inventory  
ms-pricing  
ms-report  
ms-subscription  
ms-authentication  
ms-tenant-onboarding

Rules:

- No direct DB access
- Consume data through gRPC
- DTO-first communication
- OpenAPI-first contracts
- Use Redis cache when needed
- Use Kafka mainly for cache invalidation and async events

# Final Principles

- Entity services own all persistence
- Soft delete mandatory
- No shared database ownership
- gRPC internal communication only
- REST external communication only
- Backward compatibility required
- DTO-first architecture
- MapStruct mandatory
- Java 21 minimum
- Spring Boot 3 minimum
- PostgreSQL mandatory
- Redis for cache
- Kafka for async events
- OAuth2.1 + JWT + OIDC
- Cloud-ready
- Docker-ready
- Tomcat WAR-ready