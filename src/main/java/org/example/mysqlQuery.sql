create database stock_managmentv2;
Use stock_managmentv2;

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20),
    status BOOLEAN DEFAULT TRUE
);

CREATE TABLE categories (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL
);

CREATE TABLE suppliers (
    supplier_id INT AUTO_INCREMENT PRIMARY KEY,
    supplier_name VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(100)
);

CREATE TABLE products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    category_id INT,
    supplier_id INT,
    price DECIMAL(10,2),
    stock INT,
    
    FOREIGN KEY (category_id) REFERENCES categories(category_id),
    FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id)
);

CREATE TABLE stock_movements (
    movement_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    quantity INT,
    movement_type VARCHAR(10),
    movement_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

CREATE TABLE orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    quantity INT,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

DELETE FROM categories
WHERE category_id IN (2);

INSERT INTO users(username,password,role,status)
VALUES ('admin','1234','admin',1);

INSERT INTO users(username,password,role,status)
VALUES ('emir','1234','admin',true);

<<<<<<< HEAD
INSERT INTO users(username,password,role,status)
VALUES ('tugce','1234','admin',true);

INSERT INTO users(username,password,role,status)
VALUES ('cagan','1234','admin',true);

INSERT INTO users(username,password,role,status)
VALUES ('irem','1234','staff',true);

-- 1. KATEGORİLER
INSERT INTO categories (category_name) VALUES
('Elektronik'),
('Gıda'),
('Kırtasiye'),
('Giyim'),
('Temizlik');

-- 2. TEDARİKÇİLER
INSERT INTO suppliers (supplier_name, phone, email) VALUES
('TechDağıtım A.Ş.',     '0212 555 01 01', 'info@techdagitim.com'),
('Lezzet Gıda Ltd.',      '0216 555 02 02', 'siparis@lezzetgida.com'),
('OfisStar Kırtasiye',    '0312 555 03 03', 'satis@ofisstar.com'),
('ModaTrend Tekstil',     '0232 555 04 04', 'toptan@modatrend.com'),
('TemizDünya San. Tic.',  '0322 555 05 05', 'info@temizdunya.com');

-- 3. ÜRÜNLER
-- category_id: 1=Elektronik, 2=Gıda, 3=Kırtasiye, 4=Giyim, 5=Temizlik
-- supplier_id: 1=TechDağıtım, 2=Lezzet, 3=OfisStar, 4=ModaTrend, 5=TemizDünya
INSERT INTO products (product_name, category_id, supplier_id, price, stock) VALUES
('Samsung 65" 4K TV',       1, 1, 24999.99, 15),
('iPhone 15 Pro',           1, 1, 59999.00, 8),
('Bluetooth Kulaklık',      1, 1,  1299.50, 40),
('Makarna 500g',            2, 2,    18.90, 200),
('Zeytinyağı 1L',           2, 2,   189.00, 75),
('Çikolata Kutusu',         2, 2,    85.00, 120),
('A4 Kağıt 500 Yaprak',     3, 3,    89.90, 300),
('Tükenmez Kalem (10lu)',   3, 3,    24.90, 500),
('Zımba Makinesi',          3, 3,   149.00, 60),
('Erkek Slim Fit Gömlek',   4, 4,   449.00, 90),
('Bayan Spor Ayakkabı',     4, 4,   899.00, 55),
('Çocuk Yağmurluk',         4, 4,   299.00, 40),
('Bulaşık Deterjanı 5L',    5, 5,   129.90, 150),
('Çamaşır Suyu 3L',         5, 5,    49.90, 200),
('Yüzey Temizleyici 750ml', 5, 5,    64.90, 180);

-- 4. STOK HAREKETLERİ
-- movement_type: 'IN' = stok girişi, 'OUT' = stok çıkışı
INSERT INTO stock_movements (product_id, quantity, movement_type) VALUES
(1,  20, 'IN'),   -- TV stok girişi
(1,   5, 'OUT'),  -- TV satış
(2,  10, 'IN'),   -- iPhone stok girişi
(2,   2, 'OUT'),  -- iPhone satış
(3,  50, 'IN'),   -- Kulaklık girişi
(3,  10, 'OUT'),  -- Kulaklık satış
(4, 300, 'IN'),   -- Makarna girişi
(4, 100, 'OUT'),  -- Makarna satış
(5, 100, 'IN'),   -- Zeytinyağı girişi
(5,  25, 'OUT'),  -- Zeytinyağı satış
(7, 400, 'IN'),   -- A4 Kağıt girişi
(7, 100, 'OUT'),  -- A4 Kağıt satış
(10, 120, 'IN'),  -- Gömlek girişi
(10,  30, 'OUT'), -- Gömlek satış
(13, 200, 'IN'),  -- Deterjan girişi
(13,  50, 'OUT'); -- Deterjan satış

-- 5. SİPARİŞLER
INSERT INTO orders (product_id, quantity) VALUES
(1,  2),   -- 2 adet TV
(2,  1),   -- 1 adet iPhone
(3,  5),   -- 5 adet Kulaklık
(4,  20),  -- 20 adet Makarna
(5,  10),  -- 10 adet Zeytinyağı
(6,  8),   -- 8 adet Çikolata
(7,  50),  -- 50 adet A4 Kağıt
(8,  30),  -- 30 adet Kalem
(10,  5),  -- 5 adet Gömlek
(11,  3),  -- 3 adet Ayakkabı
(13, 15),  -- 15 adet Deterjan
(14, 20);  -- 20 adet Çamaşır Suyu

-- Kontrol sorguları
SELECT * FROM categories;
SELECT * FROM suppliers;
SELECT * FROM products;
SELECT * FROM stock_movements;
SELECT * FROM orders;
=======


>>>>>>> 4aaa2ec6dc044528cbecee2290688d5f2ccb1db7
SELECT * FROM users;



