CREATE INDEX idx_price_ranges_product_id ON PRICES(product_id);
CREATE INDEX idx_products_brand_id ON PRODUCTS(brand_id);
CREATE INDEX idx_price_ranges_dates ON PRICES(start_date, end_date);
CREATE INDEX idx_price_ranges_priority ON PRICES(priority);
