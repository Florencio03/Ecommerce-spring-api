-- Table structure for table orders
CREATE TABLE orders (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  customer_id BIGINT NOT NULL,
  status VARCHAR(20) NOT NULL,
  created_at DATETIME NOT NULL,
  total_price DECIMAL(10,2) NOT NULL,
  CONSTRAINT fk_orders_user_id
	FOREIGN KEY (customer_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Table structure for table order_items
CREATE TABLE order_items (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  order_id BIGINT NOT NULL,
  product_id BIGINT NOT NULL,
  unit_price DECIMAL(10,2) NOT NULL,
  quantity INT DEFAULT 1 NOT NULL,
  total_price DECIMAL(10,2) NOT NULL,
  
  CONSTRAINT fk_order_items_order
	FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_product_id_products
	FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE ON UPDATE CASCADE
);




