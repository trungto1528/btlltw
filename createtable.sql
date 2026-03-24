use test_btlltw;
CREATE TABLE category (
    id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT
);

-- ========================
-- USER
-- ========================
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20) UNIQUE,
    address TEXT,
    role ENUM('CUSTOMER','EMPLOYEE','ADMIN') NOT NULL DEFAULT 'CUSTOMER',
    status ENUM('ACTIVE','INACTIVE') DEFAULT 'ACTIVE',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- ========================
-- PRODUCT
-- ========================
CREATE TABLE product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL CHECK (price >= 0),
    category_id INT NOT NULL,
    image_url VARCHAR(255),
    FOREIGN KEY (category_id) 
        REFERENCES category(id)
        ON DELETE CASCADE
);

-- ========================
-- PRODUCT VARIANT
-- ========================
CREATE TABLE product_variant (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    size ENUM('S','M','L','XL') NOT NULL,
    color VARCHAR(100) NOT NULL,
    stock_quantity INT NOT NULL DEFAULT 0 CHECK (stock_quantity >= 0),

    UNIQUE(product_id, size, color),

    FOREIGN KEY (product_id)
        REFERENCES product(id)
        ON DELETE CASCADE
);

-- ========================
-- CART
-- ========================
CREATE TABLE cart (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL UNIQUE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP 
        ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id)
        REFERENCES user(id)
        ON DELETE CASCADE
);

-- ========================
-- CART ITEM
-- ========================
CREATE TABLE cart_item (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cart_id INT NOT NULL,
    variant_id INT NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),

    UNIQUE(cart_id, variant_id),

    FOREIGN KEY (cart_id)
        REFERENCES cart(id)
        ON DELETE CASCADE,

    FOREIGN KEY (variant_id)
        REFERENCES product_variant(id)
);

-- ========================
-- ORDER
-- ========================
CREATE TABLE `order` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL CHECK (total_amount >= 0),
    shipping_address TEXT NOT NULL,
    status ENUM('PENDING','CONFIRMED','SHIPPING','COMPLETED','CANCELLED') 
        DEFAULT 'PENDING',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id)
        REFERENCES user(id)
);

-- ========================
-- ORDER ITEM
-- ========================
CREATE TABLE order_item (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    variant_id INT NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    price DECIMAL(10,2) NOT NULL CHECK (price >= 0),

    FOREIGN KEY (order_id)
        REFERENCES `order`(id)
        ON DELETE CASCADE,

    FOREIGN KEY (variant_id)
        REFERENCES product_variant(id)
);

-- ========================
-- PAYMENT
-- ========================
CREATE TABLE payment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT UNIQUE,
    amount DECIMAL(10,2) NOT NULL CHECK (amount >= 0),
    payment_method ENUM('COD','BANK','MOMO') NOT NULL,
    payment_date DATETIME,

    FOREIGN KEY (order_id)
        REFERENCES `order`(id)
        ON DELETE CASCADE
);

-- ========================
-- COUPON
-- ========================
CREATE TABLE coupon (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    discount_type ENUM('PERCENT','FIXED') NOT NULL,
    discount_value DECIMAL(10,2) NOT NULL CHECK (discount_value > 0),
    start_date DATETIME NOT NULL,
    end_date DATETIME NOT NULL,
    quantity INT NOT NULL CHECK (quantity >= 0),
    status ENUM('ACTIVE','EXPIRED','INACTIVE') DEFAULT 'ACTIVE'
);

-- ========================
-- ORDER_COUPON
-- ========================
CREATE TABLE order_coupon (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    coupon_id INT NOT NULL,

    UNIQUE(order_id, coupon_id),

    FOREIGN KEY (order_id)
        REFERENCES `order`(id)
        ON DELETE CASCADE,

    FOREIGN KEY (coupon_id)
        REFERENCES coupon(id)
);