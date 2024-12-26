create table IF NOT EXISTS role (
    id SERIAL NOT NULL CONSTRAINT role_pkey PRIMARY KEY,
    role_name varchar(30)
);

CREATE TABLE IF NOT EXISTS user_role (
    id SERIAL PRIMARY KEY,
    user_id integer,
    role_id integer,
    FOREIGN KEY (user_id) REFERENCES Driver (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE
);