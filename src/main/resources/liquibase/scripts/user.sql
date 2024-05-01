-- liquibase formatted sql

-- changeset ihlopachev:1

CREATE TABLE ad
(
    id    UUID PRIMARY KEY,
    author TEXT,
    image bytea,
    pk    BIGINT,
    price  DOUBLE PRECISION,
    title  TEXT
)
;
CREATE TABLE comment
(
    id              UUID PRIMARY KEY,
    author          TEXT,
    authorImage     TEXT,
    authorFirstName TEXT,
    createdAt       TIMESTAMP,
    pk              BIGINT,
    text            TEXT
);
CREATE TABLE users
(
    id         UUID PRIMARY KEY,
    email      TEXT,
    first_name TEXT,
    last_name  TEXT,
    phone      TEXT,
    role       TEXT,
    image      TEXT
);
