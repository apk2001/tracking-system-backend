# Tracking System — Backend

Spring Boot 4 REST API for the personal tracking system (sleep, weight, food, exercise, emotions, thoughts). Uses PostgreSQL with Flyway-managed schema migrations.

## Prerequisites

- Java 17+
- A locally running PostgreSQL instance

## Setup

```bash
createdb tracking_system
```

Connection settings (see `src/main/resources/application.yml`) are read from `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USER`, `DB_PASSWORD` env vars, defaulting to `localhost:5432` / `tracking_system` / `postgres` / `postgres` when unset — so local dev needs no configuration. `PORT` (default `8080`) and `APP_CORS_ALLOWED_ORIGINS` (default `http://localhost:5173`) work the same way.

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

## Deploy (Render)

`render.yaml` defines a free Postgres database plus a Docker-based web service, wiring `DB_HOST`/`DB_PORT`/`DB_NAME`/`DB_USER`/`DB_PASSWORD` from the database automatically. In the Render dashboard: **New → Blueprint**, connect this repo — Render provisions both from `render.yaml`. Flyway applies the schema migration automatically on first boot. Update `APP_CORS_ALLOWED_ORIGINS` in the Render dashboard once you know your deployed frontend's URL (defaults to `https://tracking-system-frontend.onrender.com`).
