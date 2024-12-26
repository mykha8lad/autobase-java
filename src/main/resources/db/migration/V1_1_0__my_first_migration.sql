CREATE TABLE IF NOT EXISTS public.City (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    destination FLOAT NOT NULL
);

CREATE TABLE IF NOT EXISTS public.CargoType (
    id SERIAL PRIMARY KEY,
    driver_exp INT NOT NULL,
    price FLOAT NOT NULL
);

CREATE TABLE IF NOT EXISTS public.Car (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    carrying FLOAT NOT NULL,
    is_free BOOLEAN DEFAULT TRUE,
    is_broker BOOLEAN DEFAULT FALSE,
    time_to_repair INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS public.Driver (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    num_tel VARCHAR(20) NOT NULL UNIQUE,
    experience INT NOT NULL,
    total_sum FLOAT DEFAULT 0,
    is_free BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS public."Order" (
    id SERIAL PRIMARY KEY,
    id_city INT REFERENCES City(id) ON DELETE CASCADE,
    id_cargo INT REFERENCES CargoType(id) ON DELETE CASCADE,
    weight FLOAT NOT NULL,
    is_flight BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS public.Flight (
    id SERIAL PRIMARY KEY,
    id_order INT REFERENCES "Order"(id) ON DELETE CASCADE,
    id_car INT REFERENCES Car(id) ON DELETE CASCADE,
    id_driver INT REFERENCES Driver(id) ON DELETE CASCADE,
    count_day_way INT NOT NULL
);

CREATE TABLE IF NOT EXISTS public.HistoryApp (
    id SERIAL PRIMARY KEY,
    driver_name VARCHAR(255) NOT NULL,
    driver_num_tel VARCHAR(20) NOT NULL,
    id_city INT REFERENCES City(id) ON DELETE CASCADE,
    cargo_driver_exp INT NOT NULL,
    cargo_price FLOAT NOT NULL,
    cargo_weight FLOAT NOT NULL,
    car_name VARCHAR(255) NOT NULL,
    car_carrying FLOAT NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL
);
