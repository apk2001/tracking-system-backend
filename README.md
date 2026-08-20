# Tracking System — Backend

Spring Boot 4 REST API for the personal tracking system (sleep, weight, food, exercise, emotions, thoughts). Uses PostgreSQL with Flyway-managed schema migrations.

## Prerequisites

- Java 17+
- A locally running PostgreSQL instance

## Setup

```bash
createdb tracking_system
```

Default connection settings (see `src/main/resources/application.yml`) assume `localhost:5432`, database `tracking_system`, user `postgres`, password `postgres`. Adjust `application.yml` if your local setup differs.

## Run

```bash
./mvnw spring-boot:run
```

Flyway runs the schema migration automatically on startup. The API listens on `http://localhost:8080`.

## Test

```bash
./mvnw test
```

## API

Each category exposes `POST /api/{category}` (create) and `GET /api/{category}/recent` (10 most recent, newest first):

`/api/sleep`, `/api/weight`, `/api/food`, `/api/exercise`, `/api/emotions`, `/api/thoughts`
