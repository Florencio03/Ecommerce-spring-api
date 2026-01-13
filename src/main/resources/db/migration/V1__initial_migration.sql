-- Table structure for table users
CREATE TABLE users (
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
);

-- Table structure for table addresses
CREATE TABLE addresses (
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  street VARCHAR(255) NOT NULL,
  city VARCHAR(255) NOT NULL,
  zip VARCHAR(255) NOT NULL,
  user_id BIGINT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Table structure for table tags
CREATE TABLE tags (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

-- Table structure for table user_tags (many-to-many)
CREATE TABLE user_tags (
  user_id BIGINT NOT NULL,
  tag_id INT NOT NULL,
  PRIMARY KEY (user_id, tag_id),
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  FOREIGN KEY (tag_id) REFERENCES tags(id) ON DELETE CASCADE
);

-- Table structure for table profiles (one-to-one)
CREATE TABLE profiles (
  id BIGINT NOT NULL PRIMARY KEY,
  bio TEXT NOT NULL,
  phone_number VARCHAR(15) NULL,
  date_of_birth DATE NULL,
  loyalty_points INT UNSIGNED DEFAULT 0,
  FOREIGN KEY (id) REFERENCES users(id) ON DELETE CASCADE
);


