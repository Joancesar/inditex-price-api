CREATE TABLE BRANDS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    version BIGINT,
    name VARCHAR(150) NOT NULL
);

CREATE TABLE CURRENCIES (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    version BIGINT,
    iso_code VARCHAR(3) NOT NULL UNIQUE
);

CREATE TABLE PRODUCTS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    version BIGINT,
    name VARCHAR(150) NOT NULL,
    brand_id BIGINT,
    FOREIGN KEY (brand_id) REFERENCES BRANDS(id)
);

CREATE TABLE PRICES (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    version BIGINT,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    priority INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    currency_id BIGINT,
    product_id BIGINT,
    price_list INT NOT NULL,
    FOREIGN KEY (currency_id) REFERENCES CURRENCIES(id),
    FOREIGN KEY (product_id) REFERENCES PRODUCTS(id)
);