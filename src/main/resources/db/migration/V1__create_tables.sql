CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(500),
    price DECIMAL(10, 2) NOT NULL, 
    current_stock INT NOT NULL
);

CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    product_id INT NOT NULL,
    CONSTRAINT fk_product
        FOREIGN KEY (product_id)
        REFERENCES products (id)
        ON DELETE CASCADE
);

CREATE INDEX idx_products_name ON products (name);
CREATE INDEX idx_orders_product_id ON orders (product_id);
