INSERT INTO BRANDS (id, name) VALUES (1, 'ZARA');

INSERT INTO CURRENCIES (id, iso_code) VALUES (1, 'EUR');

INSERT INTO PRODUCTS (id, name, brand_id) VALUES (35455, 'Camisa', 1);

INSERT INTO PRICES (id, start_date, end_date, priority, price, currency_id, product_id, price_list) VALUES
(1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 0, 35.50, 1, 35455, 1),
(2, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 1, 25.45, 1, 35455, 2),
(3, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 1, 30.50, 1, 35455, 3),
(4, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 1, 38.95, 1, 35455, 4);
