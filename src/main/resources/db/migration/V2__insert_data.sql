-- ===============================
-- USERS
-- ===============================
INSERT INTO users (name, email)
VALUES
('Vamsi Krishna', 'vamsi@example.com'),
('John Doe', 'john@example.com');

-- ===============================
-- ORDERS
-- ===============================
INSERT INTO orders (user_id, total_amount, order_status, created_at, updated_at)
VALUES
(1, 3000, 'CREATED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 1500, 'CREATED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ===============================
-- ORDER ITEMS
-- ===============================
INSERT INTO order_items (order_id, product_id, product_name, quantity, price)
VALUES
(1, 101, 'iPhone Case', 2, 500),
(1, 102, 'Wireless Charger', 1, 2000),
(2, 103, 'USB-C Cable', 3, 500);
