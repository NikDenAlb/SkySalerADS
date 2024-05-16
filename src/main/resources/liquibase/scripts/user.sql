-- liquibase formatted sql

-- changeset ihlopachev:1

CREATE TABLE ad
(
    user_link INT,
    image     TEXT,
    pk        INT PRIMARY KEY,
    price     INT,
    title     TEXT
)
;
CREATE TABLE comment
(
    user_link       INT,
    createdAt       TIMESTAMP,
    pk              INT PRIMARY KEY,
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
    id        INT PRIMARY KEY,
    type      VARCHAR(255),
    image     TEXT,
    user_link INT
);
CREATE TABLE ad_images
(
    id    INT PRIMARY KEY,
    type  VARCHAR(255),
    image TEXT,
    ad    INT
);
