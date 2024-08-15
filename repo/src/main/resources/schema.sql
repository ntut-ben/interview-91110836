DROP TABLE IF EXISTS MEMBER;
DROP TABLE IF EXISTS ORDER_TABLE;
DROP TABLE IF EXISTS PRODUCT;
DROP TABLE IF EXISTS ORDER_PRODUCT;

-- 創建 MEMBER 表
CREATE TABLE MEMBER (
                        ID VARCHAR(36) PRIMARY KEY,                        -- 主鍵，UUID格式
                        USERNAME VARCHAR(50) NOT NULL UNIQUE,              -- 使用者名稱，必填且唯一
                        PAXX VARCHAR(255) NOT NULL,                        -- 密碼，必填
                        EMAIL VARCHAR(100) NOT NULL UNIQUE,                -- 電子郵件，必填且唯一
                        FULL_NAME VARCHAR(200) NOT NULL,                   -- 全名，支持中文字符
                        DATE_OF_BIRTH DATE,                                -- 出生日期
                        PHONE_NUMBER VARCHAR(20),                          -- 電話號碼
                        CREATED_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 記錄創建時間，默認為當前時間
                        UPDATE_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 記錄最後更新時間，每次更新記錄時自動更新
                        STATUS VARCHAR(20) DEFAULT 'ACTIVE'                -- 狀態，默認為 'ACTIVE'
);

-- 為表和列添加註解
COMMENT ON TABLE MEMBER IS '會員資料表，存儲會員的基本信息';
COMMENT ON COLUMN MEMBER.ID IS '主鍵，UUID格式';
COMMENT ON COLUMN MEMBER.USERNAME IS '使用者名稱，必填且唯一';
COMMENT ON COLUMN MEMBER.PAXX IS '密碼，必填';
COMMENT ON COLUMN MEMBER.EMAIL IS '電子郵件，必填且唯一';
COMMENT ON COLUMN MEMBER.FULL_NAME IS '全名，支持中文字符';
COMMENT ON COLUMN MEMBER.DATE_OF_BIRTH IS '出生日期';
COMMENT ON COLUMN MEMBER.PHONE_NUMBER IS '電話號碼';
COMMENT ON COLUMN MEMBER.CREATED_TIME IS '記錄創建時間，默認為當前時間';
COMMENT ON COLUMN MEMBER.UPDATE_TIME IS '記錄最後更新時間，每次更新記錄時自動更新';
COMMENT ON COLUMN MEMBER.STATUS IS '狀態，可能的值包括：ACTIVE（活躍）、INACTIVE（不活躍）、SUSPENDED（暫停）';


CREATE TABLE PRODUCT (
                         ID VARCHAR(36) PRIMARY KEY,        -- 主鍵，UUID格式
                         NAME VARCHAR(100) NOT NULL,        -- 產品名稱，必填
                         DESCRIPTION TEXT,                  -- 產品描述
                         PRICE DECIMAL(10, 2) NOT NULL,     -- 產品價格，必填
                         STOCK INT NOT NULL,                -- 庫存量，必填
                         CREATED_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 記錄創建時間，默認為當前時間
                         UPDATE_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 記錄最後更新時間，每次更新記錄時自動更新
);

COMMENT ON TABLE PRODUCT IS '產品資料表，存儲產品的基本信息';
COMMENT ON COLUMN PRODUCT.ID IS '主鍵，UUID格式';
COMMENT ON COLUMN PRODUCT.NAME IS '產品名稱，必填';
COMMENT ON COLUMN PRODUCT.DESCRIPTION IS '產品描述';
COMMENT ON COLUMN PRODUCT.PRICE IS '產品價格，必填';
COMMENT ON COLUMN PRODUCT.STOCK IS '庫存量，必填';
COMMENT ON COLUMN PRODUCT.CREATED_TIME IS '記錄創建時間，默認為當前時間';
COMMENT ON COLUMN PRODUCT.UPDATE_TIME IS '記錄最後更新時間，每次更新記錄時自動更新';

CREATE TABLE ORDER_TABLE (
                         ID VARCHAR(36) PRIMARY KEY,            -- 主鍵，UUID格式
                         MEMBER_ID VARCHAR(36) NOT NULL,        -- 會員ID，用於關聯 MEMBER 表
                         ORDER_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 訂單日期，默認為當前時間
                         TOTAL_AMOUNT DECIMAL(10, 2) NOT NULL,  -- 訂單總額，必填
                         STATUS VARCHAR(20) DEFAULT 'PENDING',  -- 訂單狀態，默認為 'PENDING'
                         CREATED_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 記錄創建時間，默認為當前時間
                         UPDATE_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 記錄最後更新時間，每次更新記錄時自動更新
);

COMMENT ON TABLE ORDER_TABLE IS '訂單資料表，存儲訂單的基本信息';
COMMENT ON COLUMN ORDER_TABLE.ID IS '主鍵，UUID格式';
COMMENT ON COLUMN ORDER_TABLE.MEMBER_ID IS '會員ID，用於關聯 MEMBER 表';
COMMENT ON COLUMN ORDER_TABLE.ORDER_DATE IS '訂單日期，默認為當前時間';
COMMENT ON COLUMN ORDER_TABLE.TOTAL_AMOUNT IS '訂單總額，必填';
COMMENT ON COLUMN ORDER_TABLE.STATUS IS '訂單狀態，可能的值包括：PENDING（待處理）、COMPLETED（已完成）、CANCELLED（已取消）';
COMMENT ON COLUMN ORDER_TABLE.CREATED_TIME IS '記錄創建時間，默認為當前時間';
COMMENT ON COLUMN ORDER_TABLE.UPDATE_TIME IS '記錄最後更新時間，每次更新記錄時自動更新';

CREATE TABLE ORDER_PRODUCT (
                               ORDER_ID VARCHAR(36) NOT NULL,   -- 訂單ID，用於關聯 ORDER 表
                               PRODUCT_ID VARCHAR(36) NOT NULL, -- 產品ID，用於關聯 PRODUCT 表
                               QUANTITY INT NOT NULL,           -- 產品數量，必填
                               PRICE DECIMAL(10, 2) NOT NULL,   -- 單價，必填，當前訂單中產品的價格
                               PRIMARY KEY (ORDER_ID, PRODUCT_ID)  -- 複合主鍵
);

COMMENT ON TABLE ORDER_PRODUCT IS '訂單產品關聯表，存儲每個訂單中的產品信息';
COMMENT ON COLUMN ORDER_PRODUCT.ORDER_ID IS '訂單ID，用於關聯 ORDER 表';
COMMENT ON COLUMN ORDER_PRODUCT.PRODUCT_ID IS '產品ID，用於關聯 PRODUCT 表';
COMMENT ON COLUMN ORDER_PRODUCT.QUANTITY IS '產品數量，必填';
COMMENT ON COLUMN ORDER_PRODUCT.PRICE IS '單價，必填，當前訂單中產品的價格';

