-- liquibase formatted sql

-- changeset ihlopachev:1

CREATE TABLE ad
(
    author TEXT,
    image TEXT,
    pk    BIGINT PRIMARY KEY,
    price  DOUBLE PRECISION,
    title  TEXT
)
;
CREATE TABLE comment
(
    author          TEXT,
    authorImage     TEXT,
    authorFirstName TEXT,
    createdAt       TIMESTAMP,
    pk BIGINT PRIMARY KEY,
    text            TEXT
);
CREATE TABLE users
(
    id BIGINT PRIMARY KEY,
    username TEXT,
    first_name TEXT,
    last_name  TEXT,
    phone      TEXT,
    role       TEXT,
    image      TEXT
);
CREATE TABLE avatars
(
    avatar_id      BIGINT PRIMARY KEY,
    file_path      VARCHAR(255),
    file_size      BIGINT,
    media_type     VARCHAR(255),
    preview        oid,
    user_id        BIGINT
    );
