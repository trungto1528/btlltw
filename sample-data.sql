-- Insert sample categories
INSERT INTO category (category_name, description) VALUES 
('Áo Nam', 'Quần áo cỡ lớn cho nam'),
('Áo Nữ', 'Quần áo cỡ nhỏ cho nữ'),
('Quần Nam', 'Quần tây, quần jean cho nam'),
('Quần Nữ', 'Quần tây, quần jean cho nữ'),
('Phụ Kiện', 'Thắt lưng, tất, mũ, khăn');

-- Insert sample products
INSERT INTO product (product_name, price, description, image_url, category_id) VALUES 
('T-Shirt Cotton Nam', 150000, 'Áo phông cotton 100% thoáng mát', '/images/tshirt-nam-1.jpg', 1),
('Áo Sơ Mi Xanh Navy', 320000, 'Áo sơ mi chất liệu linen cao cấp', '/images/aosomi-xanhnavy.jpg', 1),
('Áo Thun Thể Thao Nữ', 180000, 'Áo phông thương hiệu nổi tiếng', '/images/athun-nu-1.jpg', 2),
('Áo Crop Top Summer', 220000, 'Áo phông cắt ngắn style Hàn', '/images/croptop-1.jpg', 2),
('Quần Jean Nam Regular', 420000, 'Quần jean xanh đen chất lượng tốt', '/images/jeans-nam-1.jpg', 3),
('Quần Tây Công Sở Nữ', 485000, 'Quần tây công sở thích hợp đi làm', '/images/quantay-nu-1.jpg', 4),
('Thắt Lưng Da Thật', 250000, 'Thắt lưng da thật chóp bạc', '/images/thatluwng-1.jpg', 5),
('Tất Cao Cấp', 85000, 'Tất cotton thoáng khí cao cấp', '/images/tat-1.jpg', 5);

UPDATE product SET image_url = CASE 
    WHEN product_name = 'T-Shirt Cotton Nam' THEN 'https://images.unsplash.com/photo-1521572267360-ee0c2909d518?q=80&w=500'
    WHEN product_name = 'Áo Sơ Mi Xanh Navy' THEN 'https://images.unsplash.com/photo-1596755094514-f87e34085b2c?q=80&w=500'
    WHEN product_name = 'Áo Thun Thể Thao Nữ' THEN 'https://images.unsplash.com/photo-1554568218-0f1715e72254?q=80&w=500'
    WHEN product_name = 'Áo Crop Top Summer' THEN 'https://images.unsplash.com/photo-1554568218-0f1715e72254?q=80&w=500'
    WHEN product_name = 'Quần Jean Nam Regular' THEN 'https://images.unsplash.com/photo-1554568218-0f1715e72254?q=80&w=500'
    WHEN product_name = 'Quần Tây Công Sở Nữ' THEN 'https://images.unsplash.com/photo-1554568218-0f1715e72254?q=80&w=500'
    WHEN product_name = 'Thắt Lưng Da Thật' THEN 'https://images.unsplash.com/photo-1554568218-0f1715e72254?q=80&w=500'
    WHEN product_name = 'Tất Cao Cấp' THEN 'https://images.unsplash.com/photo-1554568218-0f1715e72254?q=80&w=500'
    -- Thêm các dòng khác tương tự...
    ELSE image_url 
END;

-- Insert sample users
INSERT INTO user (username, password, email, full_name, phone, address, role, status, created_at) VALUES 
('admin', '$2a$10$slYQmyNdGzin7olVN3p5Be7DlH.PKZqTcmPVojLs5uXWiDmSEDtLa', 'admin@example.com', 'Quản Trị Viên', '0123456789', '123 Đường Quang Trung, TP HCM', 'ADMIN', 'ACTIVE', NOW()),
('john_doe', '$2a$10$slYQmyNdGzin7olVN3p5Be7DlH.PKZqTcmPVojLs5uXWiDmSEDtLa', 'john@example.com', 'John Doe', '0987654321', '456 Đường Nguyễn Huệ, TP HCM', 'CUSTOMER', 'ACTIVE', NOW()),
('jane_smith', '$2a$10$slYQmyNdGzin7olVN3p5Be7DlH.PKZqTcmPVojLs5uXWiDmSEDtLa', 'jane@example.com', 'Jane Smith', '0912345678', '789 Đường Lê Lợi, TP HCM', 'CUSTOMER', 'ACTIVE', NOW()),
('employee1', '$2a$10$slYQmyNdGzin7olVN3p5Be7DlH.PKZqTcmPVojLs5uXWiDmSEDtLa', 'employee1@example.com', 'Nhân Viên 1', '0945678912', '321 Đường Hai Bà Trưng, TP HCM', 'EMPLOYEE', 'ACTIVE', NOW());

-- Insert sample product variants (Size: S, M, L, XL)
INSERT INTO product_variant (product_id, size, color, stock_quantity) VALUES 
(1, 'S', 'Trắng', 15),
(1, 'M', 'Trắng', 20),
(1, 'L', 'Trắng', 18),
(1, 'XL', 'Trắng', 10),
(1, 'M', 'Đen', 12),
(1, 'L', 'Đen', 25),
(2, 'M', 'Xanh Navy', 8),
(2, 'L', 'Xanh Navy', 5),
(2, 'XL', 'Xanh Navy', 3),
(3, 'XS', 'Hồng', 10),
(3, 'S', 'Hồng', 15),
(3, 'M', 'Hồng', 12),
(3, 'S', 'Xanh Lơ', 8),
(3, 'M', 'Xanh Lơ', 20),
(4, 'S', 'Đen', 14),
(4, 'M', 'Đen', 18),
(4, 'S', 'Trắng', 12),
(5, 'S', 'Xanh Đen', 10),
(5, 'M', 'Xanh Đen', 22),
(5, 'L', 'Xanh Đen', 30),
(5, 'XL', 'Xanh Đen', 15),
(5, 'M', 'Đen', 18),
(6, 'S', 'Đen', 8),
(6, 'M', 'Đen', 12),
(6, 'L', 'Đen', 10),
(6, 'S', 'Xám', 5),
(7, '36', 'Nâu', 20),
(7, '38', 'Nâu', 18),
(7, '40', 'Nâu', 15),
(7, '36', 'Đen', 25),
(8, 'Free', 'Đen', 50),
(8, 'Free', 'Trắng', 40);

-- Insert sample coupons
INSERT INTO coupon (code, discount_type, discount_value, start_date, end_date, quantity, status) VALUES 
('SUMMER2024', 'PERCENT', 20, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 100, 'ACTIVE'),
('NEWUSER', 'FIXED', 50000, NOW(), DATE_ADD(NOW(), INTERVAL 60 DAY), 500, 'ACTIVE'),
('VIP10', 'PERCENT', 10, NOW(), DATE_ADD(NOW(), INTERVAL 90 DAY), 200, 'ACTIVE'),
('WELCOME', 'FIXED', 100000, NOW(), DATE_ADD(NOW(), INTERVAL 15 DAY), 50, 'ACTIVE');

-- Insert sample orders
INSERT INTO `order` (user_id, total_amount, shipping_address, status, created_at) VALUES 
(2, 450000, '456 Đường Nguyễn Huệ, TP HCM', 'COMPLETED', DATE_SUB(NOW(), INTERVAL 5 DAY)),
(3, 680000, '789 Đường Lê Lợi, TP HCM', 'SHIPPING', DATE_SUB(NOW(), INTERVAL 2 DAY)),
(2, 320000, '456 Đường Nguyễn Huệ, TP HCM', 'PENDING', DATE_SUB(NOW(), INTERVAL 1 DAY));

-- Insert sample order items
INSERT INTO order_item (order_id, product_variant_id) VALUES 
(1, 2),
(1, 7),
(2, 15),
(2, 24),
(3, 1);

-- Insert sample payments
INSERT INTO payment (order_id, amount, payment_method, payment_date) VALUES 
(1, 450000, 'BANK', DATE_SUB(NOW(), INTERVAL 5 DAY)),
(2, 680000, 'MOMO', DATE_SUB(NOW(), INTERVAL 2 DAY));

-- Insert sample carts
INSERT INTO cart (user_id, created_at, updated_at) VALUES 
(2, NOW(), NOW()),
(3, NOW(), NOW()),
(4, NOW(), NOW());

-- Insert sample cart items
INSERT INTO cart_item (cart_id, product_variant_id) VALUES 
(1, 5),
(1, 14),
(2, 20),
(3, 28);
