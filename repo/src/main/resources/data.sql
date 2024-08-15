INSERT INTO MEMBER (ID, USERNAME, PAXX, EMAIL, FULL_NAME, DATE_OF_BIRTH, PHONE_NUMBER, STATUS)
VALUES ('550e8400-e29b-41d4-a716-446655440000', 'taiwanuser01', 'password1', 'user01@example.com', '王小明',
        '1980-05-15', '0987654321', 'ACTIVE'),
       ('550e8400-e29b-41d4-a716-446655440001', 'taiwanuser02', 'password2', 'user02@example.com', '李美麗',
        '1992-11-20', '0988123456', 'ACTIVE'),
       ('550e8400-e29b-41d4-a716-446655440002', 'taiwanuser03', 'password3', 'user03@example.com', '陳志明',
        '1985-03-12', '0912345678', 'INACTIVE'),
       ('550e8400-e29b-41d4-a716-446655440003', 'taiwanuser04', 'password4', 'user04@example.com', '張惠妹',
        '1978-09-25', '0932123456', 'SUSPENDED'),
       ('550e8400-e29b-41d4-a716-446655440004', 'taiwanuser05', 'password5', 'user05@example.com', '林志玲',
        '1974-10-29', '0921123456', 'ACTIVE'),
       ('550e8400-e29b-41d4-a716-446655440005', 'taiwanuser06', 'password6', 'user06@example.com', '蔡依林',
        '1980-09-15', '0932123457', 'ACTIVE'),
       ('550e8400-e29b-41d4-a716-446655440006', 'taiwanuser07', 'password7', 'user07@example.com', '周杰倫',
        '1979-01-18', '0961234567', 'INACTIVE'),
       ('550e8400-e29b-41d4-a716-446655440007', 'taiwanuser08', 'password8', 'user08@example.com', '王力宏',
        '1976-05-17', '0987123456', 'SUSPENDED'),
       ('550e8400-e29b-41d4-a716-446655440008', 'taiwanuser09', 'password9', 'user09@example.com', '林俊傑',
        '1981-03-27', '0989123456', 'ACTIVE'),
       ('550e8400-e29b-41d4-a716-446655440009', 'taiwanuser10', 'password10', 'user10@example.com', '楊丞琳',
        '1984-06-04', '0986123456', 'ACTIVE'),
       ('550e8400-e29b-41d4-a716-446655440010', 'taiwanuser11', 'password11', 'user11@example.com', '蔡康永',
        '1962-03-01', '0933123456', 'ACTIVE'),
       ('550e8400-e29b-41d4-a716-446655440011', 'taiwanuser12', 'password12', 'user12@example.com', '徐若瑄',
        '1975-03-19', '0912123456', 'INACTIVE'),
       ('550e8400-e29b-41d4-a716-446655440012', 'taiwanuser13', 'password13', 'user13@example.com', '王建民',
        '1980-03-01', '0977123456', 'ACTIVE');

INSERT INTO ORDER_TABLE (ID, MEMBER_ID, ORDER_DATE, TOTAL_AMOUNT, STATUS)
VALUES
-- 會員 1 (taiwanuser01) 的訂單
('770e8400-e29b-41d4-a716-446655440000', '550e8400-e29b-41d4-a716-446655440000', '2024-08-01 10:00:00', 2500.00,
 'COMPLETED'),
('770e8400-e29b-41d4-a716-446655440001', '550e8400-e29b-41d4-a716-446655440000', '2024-08-05 14:30:00', 1500.00,
 'PENDING'),
('770e8400-e29b-41d4-a716-446655440002', '550e8400-e29b-41d4-a716-446655440000', '2024-08-10 12:30:00', 1800.00,
 'COMPLETED'),

-- 會員 2 (taiwanuser02) 的訂單
('770e8400-e29b-41d4-a716-446655440003', '550e8400-e29b-41d4-a716-446655440001', '2024-08-03 11:00:00', 3000.00,
 'COMPLETED'),
('770e8400-e29b-41d4-a716-446655440004', '550e8400-e29b-41d4-a716-446655440001', '2024-08-07 09:30:00', 2200.00,
 'CANCELLED'),

-- 會員 3 (taiwanuser03) 的訂單
('770e8400-e29b-41d4-a716-446655440005', '550e8400-e29b-41d4-a716-446655440002', '2024-08-12 10:00:00', 3200.00,
 'PENDING'),

-- 會員 4 (taiwanuser04) 的訂單
('770e8400-e29b-41d4-a716-446655440006', '550e8400-e29b-41d4-a716-446655440003', '2024-08-02 16:15:00', 1200.00,
 'CANCELLED'),
('770e8400-e29b-41d4-a716-446655440007', '550e8400-e29b-41d4-a716-446655440003', '2024-08-07 09:30:00', 1800.00,
 'PENDING'),
('770e8400-e29b-41d4-a716-446655440008', '550e8400-e29b-41d4-a716-446655440003', '2024-08-15 14:00:00', 2500.00,
 'COMPLETED'),

-- 會員 5 (taiwanuser05) 的訂單
('770e8400-e29b-41d4-a716-446655440009', '550e8400-e29b-41d4-a716-446655440004', '2024-08-10 10:45:00', 2200.00,
 'COMPLETED'),

-- 會員 6 (taiwanuser06) 的訂單
('770e8400-e29b-41d4-a716-446655440010', '550e8400-e29b-41d4-a716-446655440005', '2024-08-06 12:00:00', 3500.00,
 'PENDING'),
('770e8400-e29b-41d4-a716-446655440011', '550e8400-e29b-41d4-a716-446655440005', '2024-08-08 15:30:00', 1800.00,
 'CANCELLED'),

-- 會員 7 (taiwanuser07) 沒有訂單

-- 會員 8 (taiwanuser08) 的訂單
('770e8400-e29b-41d4-a716-446655440012', '550e8400-e29b-41d4-a716-446655440007', '2024-08-04 18:00:00', 4000.00,
 'COMPLETED'),
('770e8400-e29b-41d4-a716-446655440013', '550e8400-e29b-41d4-a716-446655440007', '2024-08-11 13:00:00', 3200.00,
 'PENDING'),

-- 會員 9 (taiwanuser09) 的訂單
('770e8400-e29b-41d4-a716-446655440014', '550e8400-e29b-41d4-a716-446655440008', '2024-08-12 15:30:00', 2800.00,
 'PENDING'),
('770e8400-e29b-41d4-a716-446655440015', '550e8400-e29b-41d4-a716-446655440008', '2024-08-14 10:20:00', 3200.00,
 'COMPLETED'),

-- 會員 10 (taiwanuser10) 的訂單
('770e8400-e29b-41d4-a716-446655440016', '550e8400-e29b-41d4-a716-446655440009', '2024-08-09 11:15:00', 1800.00,
 'CANCELLED'),
('770e8400-e29b-41d4-a716-446655440017', '550e8400-e29b-41d4-a716-446655440009', '2024-08-12 12:30:00', 2500.00,
 'COMPLETED'),
('770e8400-e29b-41d4-a716-446655440018', '550e8400-e29b-41d4-a716-446655440009', '2024-08-15 09:00:00', 3000.00,
 'PENDING'),

-- 會員 11 (taiwanuser11) 的訂單
('770e8400-e29b-41d4-a716-446655440019', '550e8400-e29b-41d4-a716-446655440010', '2024-08-13 13:45:00', 2400.00,
 'PENDING'),

-- 會員 12 (taiwanuser12) 的訂單
('770e8400-e29b-41d4-a716-446655440020', '550e8400-e29b-41d4-a716-446655440011', '2024-08-15 09:00:00', 2100.00,
 'COMPLETED');

INSERT INTO ORDER_PRODUCT (ORDER_ID, PRODUCT_ID, QUANTITY, PRICE)
VALUES
-- 訂單 770e8400-e29b-41d4-a716-446655440000 的產品
('770e8400-e29b-41d4-a716-446655440000', '660e8400-e29b-41d4-a716-446655440000', 2, 1000.00),
('770e8400-e29b-41d4-a716-446655440000', '660e8400-e29b-41d4-a716-446655440001', 1, 500.00),

-- 訂單 770e8400-e29b-41d4-a716-446655440001 的產品
('770e8400-e29b-41d4-a716-446655440001', '660e8400-e29b-41d4-a716-446655440002', 3, 500.00),

-- 訂單 770e8400-e29b-41d4-a716-446655440002 的產品
('770e8400-e29b-41d4-a716-446655440002', '660e8400-e29b-41d4-a716-446655440000', 2, 900.00),
('770e8400-e29b-41d4-a716-446655440002', '660e8400-e29b-41d4-a716-446655440002', 2, 900.00),

-- 訂單 770e8400-e29b-41d4-a716-446655440003 的產品
('770e8400-e29b-41d4-a716-446655440003', '660e8400-e29b-41d4-a716-446655440001', 2, 1500.00),

-- 訂單 770e8400-e29b-41d4-a716-446655440004 的產品
('770e8400-e29b-41d4-a716-446655440004', '660e8400-e29b-41d4-a716-446655440002', 1, 2200.00),

-- 訂單 770e8400-e29b-41d4-a716-446655440005 的產品
('770e8400-e29b-41d4-a716-446655440005', '660e8400-e29b-41d4-a716-446655440000', 4, 800.00),

-- 訂單 770e8400-e29b-41d4-a716-446655440006 的產品
('770e8400-e29b-41d4-a716-446655440006', '660e8400-e29b-41d4-a716-446655440000', 2, 1000.00),

-- 訂單 770e8400-e29b-41d4-a716-446655440007 的產品
('770e8400-e29b-41d4-a716-446655440007', '660e8400-e29b-41d4-a716-446655440002', 1, 1800.00),

-- 訂單 770e8400-e29b-41d4-a716-446655440008 的產品
('770e8400-e29b-41d4-a716-446655440008', '660e8400-e29b-41d4-a716-446655440001', 2, 1250.00),

-- 訂單 770e8400-e29b-41d4-a716-446655440009 的產品
('770e8400-e29b-41d4-a716-446655440009', '660e8400-e29b-41d4-a716-446655440002', 2, 2200.00),

-- 訂單 770e8400-e29b-41d4-a716-446655440010 的產品
('770e8400-e29b-41d4-a716-446655440010', '660e8400-e29b-41d4-a716-446655440000', 1, 3500.00),

-- 訂單 770e8400-e29b-41d4-a716-446655440011 的產品
('770e8400-e29b-41d4-a716-446655440011', '660e8400-e29b-41d4-a716-446655440001', 3, 600.00),

-- 訂單 770e8400-e29b-41d4-a716-446655440012 的產品
('770e8400-e29b-41d4-a716-446655440012', '660e8400-e29b-41d4-a716-446655440000', 1, 3200.00),

-- 訂單 770e8400-e29b-41d4-a716-446655440013 的產品
('770e8400-e29b-41d4-a716-446655440013', '660e8400-e29b-41d4-a716-446655440002', 3, 4500.00),

-- 訂單 770e8400-e29b-41d4-a716-446655440014 的產品
('770e8400-e29b-41d4-a716-446655440014', '660e8400-e29b-41d4-a716-446655440000', 2, 1400.00),

-- 訂單 770e8400-e29b-41d4-a716-446655440015 的產品
('770e8400-e29b-41d4-a716-446655440015', '660e8400-e29b-41d4-a716-446655440001', 3, 1600.00),

-- 訂單 770e8400-e29b-41d4-a716-446655440016 的產品
('770e8400-e29b-41d4-a716-446655440016', '660e8400-e29b-41d4-a716-446655440002', 4, 1800.00),

-- 訂單 770e8400-e29b-41d4-a716-446655440017 的產品
('770e8400-e29b-41d4-a716-446655440017', '660e8400-e29b-41d4-a716-446655440001', 2, 2500.00),

-- 訂單 770e8400-e29b-41d4-a716-446655440018 的產品
('770e8400-e29b-41d4-a716-446655440018', '660e8400-e29b-41d4-a716-446655440000', 1, 3000.00),

-- 訂單 770e8400-e29b-41d4-a716-446655440019 的產品
('770e8400-e29b-41d4-a716-446655440019', '660e8400-e29b-41d4-a716-446655440002', 3, 2400.00),

-- 訂單 770e8400-e29b-41d4-a716-446655440020 的產品
('770e8400-e29b-41d4-a716-446655440020', '660e8400-e29b-41d4-a716-446655440001', 2, 2100.00);

INSERT INTO PRODUCT (ID, NAME, DESCRIPTION, PRICE, STOCK)
VALUES ('660e8400-e29b-41d4-a716-446655440000', '產品A', '高品質產品A', 1000.00, 50),
       ('660e8400-e29b-41d4-a716-446655440001', '產品B', '高品質產品B', 1500.00, 30),
       ('660e8400-e29b-41d4-a716-446655440002', '產品C', '高品質產品C', 2000.00, 20),
       ('660e8400-e29b-41d4-a716-446655440003', '產品D', '高品質產品D', 2500.00, 15),
       ('660e8400-e29b-41d4-a716-446655440004', '產品E', '高品質產品E', 3000.00, 10),
       ('660e8400-e29b-41d4-a716-446655440005', '產品F', '高品質產品F', 3500.00, 25),
       ('660e8400-e29b-41d4-a716-446655440006', '產品G', '高品質產品G', 4000.00, 40),
       ('660e8400-e29b-41d4-a716-446655440007', '產品H', '高品質產品H', 4500.00, 35),
       ('660e8400-e29b-41d4-a716-446655440008', '產品I', '高品質產品I', 5000.00, 45),
       ('660e8400-e29b-41d4-a716-446655440009', '產品J', '高品質產品J', 5500.00, 60);
