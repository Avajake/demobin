-- Создание таблицы пользователей
CREATE TABLE Users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    login VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

-- Создание таблицы типов изделий
CREATE TABLE ProductTypes (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    product_details_id INT UNIQUE REFERENCES ProductDetails(id)
);

-- Создание таблицы деталей изделий
CREATE TABLE ProductDetails (
    id SERIAL PRIMARY KEY,
    weight DECIMAL(10,2) NOT NULL,
    length DECIMAL(10, 2) NOT NULL,
    width DECIMAL(10, 2) NOT NULL,
    height DECIMAL(10, 2) NOT NULL
);

-- Создание таблицы цветов
CREATE TABLE Colors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

-- Создание таблицы склада
CREATE TABLE Stock (
    id SERIAL PRIMARY KEY,
    product_id INT REFERENCES ProductTypes(id),
    color_id INT REFERENCES Colors(id),
    quantity INT NOT NULL
);

-- Создание таблицы маркетплейсов
CREATE TABLE Marketplaces (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL
);

-- Обновление таблицы отгрузок
CREATE TABLE Shipments (
    id SERIAL PRIMARY KEY,
    product_id INT REFERENCES ProductTypes(id),
    quantity INT NOT NULL,
    marketplace_id INT REFERENCES Marketplaces(id),  -- Внешний ключ к таблице Marketplaces
    shipment_date TIMESTAMP NOT NULL DEFAULT NOW()
);