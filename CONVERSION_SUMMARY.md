# Chuyển đổi từ JPA sang Pure DAO - Tóm tắt Thay đổi

## Tồn tại các bước thực hiện:

### 1. **Loại bỏ JPA Annotations từ Model Classes**
   - Xóa `@Entity`, `@Id`, `@GeneratedValue`, `@Table`, `@Column`, `@ManyToOne`, `@OneToOne`, `@Enumerated` 
   - Chuyển các đối tượng liên kết (relationships) sang IDs. Ví dụ:
     - `User user` → `Long userId`
     - `Category category` → `Long categoryId`
     - `Order order` → `Long orderId`

### 2. **Tạo Pure DAO Classes**
   - Tạo thư mục `/dao` với BaseDAO và các DAO class cụ thể:
     - **BaseDAO**: Class cơ sở cung cấp kết nối DataSource
     - **UserDAO**: CRUD operations cho User
     - **ProductDAO**: CRUD operations cho Product
     - **CategoryDAO**: CRUD operations cho Category
     - **CartDAO**: CRUD operations cho Cart
     - **CartItemDAO**: CRUD operations cho CartItem
     - **OrderDAO**: CRUD operations cho Order
     - **OrderItemDAO**: CRUD operations cho OrderItem
     - **PaymentDAO**: CRUD operations cho Payment
     - **CouponDAO**: CRUD operations cho Coupon
     - **ProductVariantDAO**: CRUD operations cho ProductVariant
     - **OrderCouponDAO**: CRUD operations cho OrderCoupon

   Mỗi DAO class bao gồm:
   - `findAll()`: Lấy tất cả records
   - `findById(Long id)`: Lấy theo ID
   - `save(Entity)`: Thêm record
   - `update(Entity)`: Cập nhật record
   - `delete(Long id)`: Xóa record
   - Các method tùy chỉnh khác dựa trên repository methods

### 3. **Cập nhật Services**
   - Thay thế `@Autowired private XRepository` bằng `@Autowired private XDAO`
   - Cập nhật toàn bộ calls từ repository sang DAO
   
   **Ví dụ:**
   ```java
   // Trước (với JPA)
   userRepository.findByUsername(username)
   
   // Sau (với DAO)
   userDAO.findByUsername(username)
   ```

### 4. **Cập nhật pom.xml**
   - **Xóa**: `spring-boot-starter-data-jpa` (JPA/Hibernate dependency)
   - **Thêm**: `spring-boot-starter-jdbc` (JDBC dependency)
   - **Thêm**: `HikariCP` (connection pooling)

### 5. **Tạo Database Configuration**
   - Tạo `DatabaseConfig.java` class để cấu hình HikariCP DataSource
   - Cấu hình thông số DataSource từ `application.yaml`:
     - JDBC URL
     - Username/Password
     - Driver Class
     - Pool size, timeout, etc.

### 6. **Cập nhật application.yaml**
   - Xóa JPA configuration section:
     ```yaml
     spring:
       jpa:
         hibernate:
           ddl-auto: none
         show-sql: false
         properties:
           hibernate:
             dialect: org.hibernate.dialect.MySQLDialect
     ```
   - Giữ lại JDBC datasource configuration

### 7. **Xóa Old Repository Files**
   - Xóa toàn bộ các file interface repository cũ từ `/repository` folder

## Các file được tạo:
- `/src/main/java/com/btl/ltw/dao/BaseDAO.java`
- `/src/main/java/com/btl/ltw/dao/UserDAO.java`
- `/src/main/java/com/btl/ltw/dao/ProductDAO.java`
- `/src/main/java/com/btl/ltw/dao/CategoryDAO.java`
- `/src/main/java/com/btl/ltw/dao/CartDAO.java`
- `/src/main/java/com/btl/ltw/dao/CartItemDAO.java`
- `/src/main/java/com/btl/ltw/dao/OrderDAO.java`
- `/src/main/java/com/btl/ltw/dao/OrderItemDAO.java`
- `/src/main/java/com/btl/ltw/dao/PaymentDAO.java`
- `/src/main/java/com/btl/ltw/dao/CouponDAO.java`
- `/src/main/java/com/btl/ltw/dao/ProductVariantDAO.java`
- `/src/main/java/com/btl/ltw/dao/OrderCouponDAO.java`
- `/src/main/java/com/btl/ltw/config/DatabaseConfig.java`

## Các file được sửa:
- Tất cả các model classes (User, Product, Category, Cart, Order, v.v.)
- `pom.xml`
- `src/main/resources/application.yaml`
- `src/main/java/com/btl/ltw/services/UserService.java`
- `src/main/java/com/btl/ltw/services/ProductService.java`
- `src/main/java/com/btl/ltw/services/CustomUserDetailsService.java`

## Các file bị xóa:
- Tất cả các file repository interface từ `/src/main/java/com/btl/ltw/repository/`

## Kết quả Compilation:
✅ **BUILD SUCCESS** - Project compiles successfully mà không có lỗi

## Ưu điểm của Pure DAO:
1. **Toàn quyền kiểm soát SQL**: Viết SQL tối ưu hóa theo yêu cầu
2. **Giảm overhead**: Không có Hibernate auto-mapping
3. **Hiệu suất tốt hơn**: JDBC trực tiếp nhanh hơn ORM
4. **Linh hoạt**: Dễ dàng thực hiện queries phức tạp
5. **Đơn giản hơn**: DAO pattern dễ hiểu so với JPA

## Lưu ý:
- Các SQL queries được tối ưu hóa cho MySQL
- Exception handling có thể cần được cải thiện (hiện tại chỉ print stack trace)
- Transaction management cần được thiết lập nếu cần (có thể sử dụng Spring's `@Transactional`)
