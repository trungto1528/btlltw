-- 1. Category
INSERT INTO category (category_name, description) VALUES 
('Áo Nam', 'Các loại áo thun, sơ mi, áo khoác dành cho nam giới'),
('Áo Nữ', 'Thời trang áo nữ đa dạng phong cách'),
('Quần Nam', 'Quần jean, quần tây, quần short nam'),
('Quần Nữ', 'Quần vải, quần jean và chân váy nữ'),
('Phụ Kiện', 'Thắt lưng, tất, mũ và các phụ kiện khác');

-- 2. User (Password mặc định: 123456 - Đã băm Bcrypt)
INSERT INTO user (username, password, full_name, email, phone, address, role) VALUES 
('admin', '$2a$10$R/3lY.FvP8L1Y7X.T7f.2O.S6v6F4K8Vv4Q.uG9Y6C4uG9Y6C4uG9', 'Quản Trị Viên', 'admin@shop.com', '0123456789', 'Hà Nội', 'ADMIN'),
('khachhang1', '$2a$10$R/3lY.FvP8L1Y7X.T7f.2O.S6v6F4K8Vv4Q.uG9Y6C4uG9Y6C4uG9', 'Trần Khách Hàng', 'customer1@gmail.com', '0912345678', 'TP. HCM', 'CUSTOMER');

-- 3. Product
INSERT INTO product (product_name, description, price, category_id, image_url) VALUES 
('T-Shirt Cotton Basic', 'Áo thun 100% cotton thoáng mát', 150000, 1, 'https://images.unsplash.com/photo-1521572267360-ee0c2909d518?q=80&w=500'),
('Sơ Mi Oxford Blue', 'Sơ mi thanh lịch cho dân công sở', 350000, 1, 'https://images.unsplash.com/photo-1596755094514-f87e34085b2c?q=80&w=500'),
('Áo Hoodie Streetwear', 'Chất nỉ dày dặn, phong cách đường phố', 450000, 1, 'https://images.unsplash.com/photo-1556821840-3a63f95609a7?q=80&w=500'),
('Váy Hoa Nhí Summer', 'Váy lụa nhẹ nhàng cho mùa hè', 280000, 2, 'https://images.unsplash.com/photo-1572804013307-a9a111d72f8b?q=80&w=500'),
('Quần Jean Slim Fit', 'Jean co giãn nhẹ, tôn dáng', 420000, 3, 'https://images.unsplash.com/photo-1542272604-787c3835535d?q=80&w=500'),
('Thắt Lưng Da Bò', 'Da thật 100%, khóa kim loại', 250000, 5, 'https://images.unsplash.com/photo-1624222247344-550fb60583dc?q=80&w=500');

-- 4. Product Variant
INSERT INTO product_variant (product_id, size, color, stock_quantity) VALUES 
(1, 'M', 'Trắng', 50), (1, 'L', 'Trắng', 30), (1, 'M', 'Đen', 40),
(2, 'L', 'Xanh Blue', 20), (2, 'XL', 'Xanh Blue', 15),
(3, 'M', 'Xám', 10), (3, 'L', 'Xám', 25),
(4, 'S', 'Hoa Đỏ', 12), (4, 'M', 'Hoa Đỏ', 18),
(5, 'L', 'Xanh Đậm', 35), (6, 'M', 'Nâu', 100);

-- 5. Coupon
INSERT INTO coupon (code, discount_type, discount_value, start_date, end_date, quantity) VALUES 
('HELLO2026', 'PERCENT', 10.00, '2026-01-01 00:00:00', '2026-12-31 23:59:59', 100);

-- 6. Cart cho User 2
INSERT INTO cart (user_id) VALUES (2);