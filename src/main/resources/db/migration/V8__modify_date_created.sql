ALTER TABLE carts
MODIFY date_created DATE NOT NULL DEFAULT (curdate());
