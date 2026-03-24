-- Create User table
CREATE TABLE IF NOT EXISTS user (
  id BIGINT NOT NULL AUTO_INCREMENT,
  username VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  full_name VARCHAR(255),
  phone VARCHAR(20),
  address VARCHAR(500),
  role VARCHAR(50),
  status VARCHAR(50),
  created_at DATETIME(6),
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create Category table
CREATE TABLE IF NOT EXISTS category (
  id BIGINT NOT NULL AUTO_INCREMENT,
  category_name VARCHAR(255),
  description VARCHAR(500),
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create Product table
CREATE TABLE IF NOT EXISTS product (
  id BIGINT NOT NULL AUTO_INCREMENT,
  product_name VARCHAR(255),
  price DOUBLE,
  description VARCHAR(500),
  image_url VARCHAR(500),
  category_id BIGINT,
  PRIMARY KEY (id),
  FOREIGN KEY (category_id) REFERENCES category (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create ProductVariant table
CREATE TABLE IF NOT EXISTS product_variant (
  id BIGINT NOT NULL AUTO_INCREMENT,
  size VARCHAR(50),
  color VARCHAR(100),
  stock_quantity INT,
  product_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (product_id) REFERENCES product (id),
  UNIQUE KEY uk_variant (product_id, size, color)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create Cart table
CREATE TABLE IF NOT EXISTS cart (
  id BIGINT NOT NULL AUTO_INCREMENT,
  user_id BIGINT,
  created_at DATETIME(6),
  updated_at DATETIME(6),
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user (id),
  UNIQUE KEY uk_user (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create CartItem table
CREATE TABLE IF NOT EXISTS cart_item (
  id BIGINT NOT NULL AUTO_INCREMENT,
  cart_id BIGINT,
  product_variant_id BIGINT,
  PRIMARY KEY (id),
  FOREIGN KEY (cart_id) REFERENCES cart (id),
  FOREIGN KEY (product_variant_id) REFERENCES product_variant (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create Order table
CREATE TABLE IF NOT EXISTS `order` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  user_id BIGINT,
  total_amount DOUBLE,
  shipping_address VARCHAR(500),
  status VARCHAR(50),
  created_at DATETIME(6),
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create OrderItem table
CREATE TABLE IF NOT EXISTS order_item (
  id BIGINT NOT NULL AUTO_INCREMENT,
  order_id BIGINT,
  product_variant_id BIGINT,
  PRIMARY KEY (id),
  FOREIGN KEY (order_id) REFERENCES `order` (id),
  FOREIGN KEY (product_variant_id) REFERENCES product_variant (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create Payment table
CREATE TABLE IF NOT EXISTS payment (
  id BIGINT NOT NULL AUTO_INCREMENT,
  order_id BIGINT,
  amount DOUBLE,
  payment_method VARCHAR(50),
  payment_date DATETIME(6),
  PRIMARY KEY (id),
  FOREIGN KEY (order_id) REFERENCES `order` (id),
  UNIQUE KEY uk_order (order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create Coupon table
CREATE TABLE IF NOT EXISTS coupon (
  id BIGINT NOT NULL AUTO_INCREMENT,
  code VARCHAR(100) UNIQUE,
  discount_type VARCHAR(50),
  discount_value DOUBLE,
  start_date DATETIME(6),
  end_date DATETIME(6),
  quantity INT,
  status VARCHAR(50),
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create OrderCoupon table
CREATE TABLE IF NOT EXISTS order_coupon (
  id BIGINT NOT NULL AUTO_INCREMENT,
  order_id BIGINT,
  coupon_id BIGINT,
  PRIMARY KEY (id),
  FOREIGN KEY (order_id) REFERENCES `order` (id),
  FOREIGN KEY (coupon_id) REFERENCES coupon (id),
  UNIQUE KEY uk_order_coupon (order_id, coupon_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
