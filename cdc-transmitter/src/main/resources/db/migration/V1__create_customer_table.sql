CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS customer (
    id uuid DEFAULT uuid_generate_v4() NOT NULL,
    username VARCHAR NOT NULL,
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    ssn VARCHAR,
    phone_number VARCHAR,
    email VARCHAR NOT NULL,
    PRIMARY KEY(id)
);
