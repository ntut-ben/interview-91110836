DROP TABLE IF EXISTS MEMBER;
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
