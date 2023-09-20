CREATE TABLE card_access(
    card_access_id serial PRIMARY KEY,
    level_access CHAR(5) NOT NULL CHECK (level_access IN ('ONE', 'TWO', 'THREE') AND (level_access !=''))
);

CREATE TABLE employees
(
    employees_id serial PRIMARY KEY,
    surname VARCHAR(25) NOT NULL,
    department VARCHAR(25) NOT NULL,
    date_of_employment DATE NOT NULL,
    card_access_id INT UNIQUE,
    FOREIGN KEY (card_access_id) REFERENCES card_access (card_access_id) ON DELETE CASCADE
)