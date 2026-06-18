# Endpoint Convention Rules

All REST endpoints must follow the standard pattern in singular:

```text
/api/[version]/[service-type]/[service-name]
```

## Examples

### Entity Services

```text
/api/v1/es/user
/api/v1/es/product
/api/v1/es/branch
/api/v1/es/customer
```

### Microservices

```text
/api/v1/ms/checkout
/api/v1/ms/inventory
/api/v1/ms/pricing
```

### Task Services

```text
/api/v1/ts/cache-sync
/api/v1/ts/report-generator
```

### Utility Services

```text
/api/v1/us/auth
/api/v1/us/storage
```

---

# Endpoint Rules

* All endpoints must be versioned
* Versioning is mandatory
* Use kebab-case for endpoint names
* Never expose internal implementation details in URLs
* Keep endpoint naming consistent across all services
* REST endpoints are external-only
* Internal communication must use gRPC exclusively

---

# Resource Examples

```text
GET    /api/v1/es/user
GET    /api/v1/es/user/{id}
POST   /api/v1/es/user
PUT    /api/v1/es/user/{id}
DELETE /api/v1/es/user/{id}
PATCH  /api/v1/es/user/{id}/restore
```

Soft delete restoration endpoints must use:

```text
/restore
```

instead of physical deletion.
