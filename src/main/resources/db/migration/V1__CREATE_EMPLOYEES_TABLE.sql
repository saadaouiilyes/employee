CREATE SEQUENCE employees_id_seq;

CREATE TABLE employees (
    id bigint PRIMARY KEY ,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    email VARCHAR(255),
    salary double_precision NOT NULL
);
-- Finally, alter the sequence ownership
