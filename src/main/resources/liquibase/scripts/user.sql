-- liquibase formatted sql

-- changeset ihlopachev:1

CREATE TABLE ad
(
    user_link TEXT,
    image     TEXT,
    pk        INT PRIMARY KEY,
    price     INT,
    title     TEXT
)
;
CREATE TABLE comment
(
    user_id         TEXT,
    authorImage     TEXT,
    authorFirstName TEXT,
    createdAt       TIMESTAMP,
    pk              BIGINT PRIMARY KEY,
    text            TEXT
);
CREATE TABLE users
(

    id         INT PRIMARY KEY,

    username   TEXT,
    first_name TEXT,
    last_name  TEXT,
    phone      TEXT,
    role       TEXT,
    avatar     TEXT
);
CREATE TABLE avatars
(
    avatar_id  BIGINT PRIMARY KEY,
    file_path  VARCHAR(255),
    file_size  BIGINT,
    media_type VARCHAR(255),
    preview    TEXT,
    user_id    BIGINT
);
