-- liquibase formatted sql

-- changeset ihlopachev:1

CREATE TABLE ad
(
    author TEXT,
    image  TEXT,
    pk BIGINT PRIMARY KEY,
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
CREATE TABLE create_or_update_ad
(
    id          UUID PRIMARY KEY,
    title       TEXT,
    price       DOUBLE PRECISION,
    description TEXT
);
CREATE TABLE create_or_update_comment
(
    id   UUID PRIMARY KEY,
    text TEXT
);
CREATE TABLE login
(
    id       UUID PRIMARY KEY,
    username TEXT,
    password TEXT
);
CREATE TABLE new_password
(
    id              UUID PRIMARY KEY,
    currentPassword TEXT,
    newPassword     TEXT
);
CREATE TABLE register
(
    id         UUID PRIMARY KEY,
    username   TEXT,
    password   TEXT,
    first_name TEXT,
    last_name  TEXT,
    phone      TEXT,
    role       TEXT
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