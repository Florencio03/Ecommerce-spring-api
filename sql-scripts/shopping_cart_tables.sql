-- Table structure for table cart
CREATE TABLE carts (
  id BINARY(16) DEFAULT (uuid_to_bin(uuid())) NOT NULL PRIMARY KEY,
  date_created DATE NOT NULL
);

-- Table structure for table cart_items
CREATE TABLE cart_items (
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  cart_id BINARY(16) NOT NULL,
  product_id BIGINT NOT NULL,
  quantity INT DEFAULT 1 NOT NULL,
 
  CONSTRAINT cart_item_cart_product_unique 
	UNIQUE (cart_id, product_id),
  CONSTRAINT fk_cart_item_on_carts_id 
	FOREIGN KEY (cart_id) REFERENCES carts(id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_cart_item_on_product_id 
	FOREIGN KEY (product_id) REFERENCES products (id)
);




