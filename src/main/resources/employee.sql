CREATE TABLE card_access(
    id serial PRIMARY KEY,
    level_access char(5) not null
);

CREATE TABLE employee
(
    id serial PRIMARY KEY,
    surname varchar(250) not null,
    department varchar(250),
    date_of_employment date not null,
    id_card_access int unique,
    FOREIGN KEY (id_card_access) REFERENCES card_access (id) ON DELETE CASCADE
)