--------------------------------------------------------
--  DDL for Table MYBANK_APP_ACCOUNT
--------------------------------------------------------

CREATE TABLE MYBANK_APP_ACCOUNT 
   (	ACCOUNT_ID NUMBER, 
	CUSTOMER_ID NUMBER, 
	ACCOUNT_TYPE VARCHAR2(50 BYTE), 
	ACCOUNT_NUMBER NUMBER, 
	ACCOUNT_STATUS VARCHAR2(50 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE USERS;

--------------------------------------------------------
--  DDL for Table MYBANK_APP_CUSTOMER
--------------------------------------------------------

CREATE TABLE MYBANK_APP_CUSTOMER 
   (	CUSTOMER_ID NUMBER, 
	CUSTOMER_NAME VARCHAR2(255 BYTE), 
	CUSTOMER_ADDRESS VARCHAR2(255 BYTE), 
	CUSTOMER_STATUS VARCHAR2(50 BYTE), 
	CUSTOMER_CONTACT NUMBER, 
	USERNAME VARCHAR2(50 BYTE), 
	PASSWORD VARCHAR2(50 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE USERS;

--------------------------------------------------------
--  DDL for Table MYBANK_APP_DEBITCARD
--------------------------------------------------------

CREATE TABLE MYBANK_APP_DEBITCARD 
   (	DEBITCARD_NUMBER NUMBER, 
	ACCOUNT_NUMBER NUMBER, 
	CUSTOMER_ID NUMBER, 
	DEBITCARD_CVV NUMBER(*,0), 
	DEBITCARD_PIN NUMBER(*,0), 
	DEBITCARD_EXPIRY DATE, 
	DEBITCARD_STATUS VARCHAR2(50 BYTE), 
	DEBITCARD_DOMESTIC_LIMIT NUMBER(15,2), 
	DEBITCARD_INTERNATIONAL_LIMIT NUMBER(15,2)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE USERS;

--------------------------------------------------------
--  DDL for Table MYBANK_APP_DEPOSITSAVAILABLE
--------------------------------------------------------

CREATE TABLE MYBANK_APP_DEPOSITSAVAILABLE 
   (	DEPOSIT_ID NUMBER, 
	DEPOSIT_NAME VARCHAR2(255 BYTE), 
	DEPOSIT_ROI NUMBER(15,2), 
	DEPOSIT_TYPE VARCHAR2(50 BYTE), 
	DEPOSIT_DESCRIPTION VARCHAR2(255 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE USERS;

--------------------------------------------------------
--  DDL for Table MYBANK_APP_DEPOSITSAVAILED
--------------------------------------------------------

CREATE TABLE MYBANK_APP_DEPOSITSAVAILED 
   (	DEPOSIT_AVAIL_ID NUMBER, 
	CUSTOMER_ID NUMBER, 
	DEPOSIT_ID NUMBER, 
	DEPOSITED_AMOUNT NUMBER(15,2), 
	DEPOSITED_DURATION NUMBER(*,0), 
	DEPOSIT_MATURITY DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE USERS;

--------------------------------------------------------
--  DDL for Table MYBANK_APP_INSURANCEAVAILABLE
--------------------------------------------------------

CREATE TABLE MYBANK_APP_INSURANCEAVAILABLE 
   (	INSURANCE_ID NUMBER, 
	INSURANCE_TYPE VARCHAR2(50 BYTE), 
	INSURANCE_NAME VARCHAR2(255 BYTE), 
	INSURANCE_KEY_BENEFITS VARCHAR2(255 BYTE), 
	INSURANCE_LIFETIME NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE USERS;

--------------------------------------------------------
--  DDL for Table MYBANK_APP_INSURANCEAVAILED
--------------------------------------------------------

CREATE TABLE MYBANK_APP_INSURANCEAVAILED 
   (	INSURANCE_AVAILED_ID NUMBER, 
	CUSTOMER_ID NUMBER, 
	INSURANCE_ID NUMBER, 
	INSURANCE_COVERAGE NUMBER(15,2), 
	INSURANCE_PREMIUM NUMBER(15,2)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE USERS;

--------------------------------------------------------
--  DDL for Table MYBANK_APP_KYC
--------------------------------------------------------

CREATE TABLE MYBANK_APP_KYC 
   (	KYC_NUMBER NUMBER, 
	CUSTOMER_ID NUMBER, 
	KYC_PAN VARCHAR2(50 BYTE), 
	KYC_AADHAAR NUMBER, 
	KYC_STATUS VARCHAR2(50 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE USERS;

--------------------------------------------------------
--  DDL for Table MYBANK_APP_LOANAVAILABLE
--------------------------------------------------------

CREATE TABLE MYBANK_APP_LOANAVAILABLE 
   (	LOAN_ID NUMBER, 
	LOAN_TYPE VARCHAR2(50 BYTE), 
	LOAN_NAME VARCHAR2(255 BYTE), 
	LOAN_DESCRIPTION VARCHAR2(255 BYTE), 
	LOAN_ROI NUMBER(15,2)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE USERS;

--------------------------------------------------------
--  DDL for Table MYBANK_APP_LOANAVAILED
--------------------------------------------------------

CREATE TABLE MYBANK_APP_LOANAVAILED 
   (	LOAN_APP_ID NUMBER, 
	CUSTOMER_ID NUMBER, 
	LOAN_ID NUMBER, 
	LOAN_AMOUNT NUMBER(15,2), 
	LOAN_EMI NUMBER(15,2), 
	LOAN_TENURE NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE USERS;

--------------------------------------------------------
--  DDL for Table MYBANK_APP_PAYEE
--------------------------------------------------------

CREATE TABLE MYBANK_APP_PAYEE 
   (	PAYEE_ID NUMBER, 
	CUSTOMER_ID NUMBER, 
	ACCOUNT_NUMBER NUMBER, 
	PAYEE_NAME VARCHAR2(255 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE USERS;

--------------------------------------------------------
--  DDL for Table MYBANK_APP_TRANSACTION
--------------------------------------------------------

CREATE TABLE MYBANK_APP_TRANSACTION 
   (	TRANSACTION_ID NUMBER, 
	TRANSACTION_TYPE VARCHAR2(50 BYTE), 
	TRANSACTION_FROM NUMBER, 
	TRANSACTION_TO NUMBER, 
	TRANSACTION_DATE DATE, 
	TRANSACTION_AMOUNT NUMBER(15,2), 
	TRANSACTION_STATUS VARCHAR2(50 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE USERS;

Insert into MYBANK_APP_ACCOUNT (ACCOUNT_ID,CUSTOMER_ID,ACCOUNT_TYPE,ACCOUNT_NUMBER,ACCOUNT_STATUS) values (200001,100001,'current',8529637410,'active');
Insert into MYBANK_APP_ACCOUNT (ACCOUNT_ID,CUSTOMER_ID,ACCOUNT_TYPE,ACCOUNT_NUMBER,ACCOUNT_STATUS) values (200002,100002,'savings',7418529630,'active');

Insert into MYBANK_APP_CUSTOMER (CUSTOMER_ID,CUSTOMER_NAME,CUSTOMER_ADDRESS,CUSTOMER_STATUS,CUSTOMER_CONTACT,USERNAME,PASSWORD) values (100001,'shreyas','udupi','active',7418529630,'shreyas12','shreyas123');
Insert into MYBANK_APP_CUSTOMER (CUSTOMER_ID,CUSTOMER_NAME,CUSTOMER_ADDRESS,CUSTOMER_STATUS,CUSTOMER_CONTACT,USERNAME,PASSWORD) values (100002,'varun','moodbidri','active',9638527410,'varun12','varun123');

Insert into MYBANK_APP_DEPOSITSAVAILABLE (DEPOSIT_ID,DEPOSIT_NAME,DEPOSIT_ROI,DEPOSIT_TYPE,DEPOSIT_DESCRIPTION) values (1000001,'FD',10.2,'Lump Sum','Standard Fixed Deposit');
Insert into MYBANK_APP_DEPOSITSAVAILABLE (DEPOSIT_ID,DEPOSIT_NAME,DEPOSIT_ROI,DEPOSIT_TYPE,DEPOSIT_DESCRIPTION) values (1000002,'RD',9.2,'Recurring','Standard Recurring Deposit');
Insert into MYBANK_APP_DEPOSITSAVAILABLE (DEPOSIT_ID,DEPOSIT_NAME,DEPOSIT_ROI,DEPOSIT_TYPE,DEPOSIT_DESCRIPTION) values (1000003,'Senior Citizen FD',11.5,'Lump Sum','Senior Citizen Fixed Deposit');
Insert into MYBANK_APP_DEPOSITSAVAILABLE (DEPOSIT_ID,DEPOSIT_NAME,DEPOSIT_ROI,DEPOSIT_TYPE,DEPOSIT_DESCRIPTION) values (1000004,'Employee FD',10.7,'Lump Sum','Employee Fixed Deposit');
Insert into MYBANK_APP_DEPOSITSAVAILABLE (DEPOSIT_ID,DEPOSIT_NAME,DEPOSIT_ROI,DEPOSIT_TYPE,DEPOSIT_DESCRIPTION) values (1000005,'Women Empowerment RD',10.2,'Recurring','Women Empowerment Recurring Deposit');

Insert into MYBANK_APP_DEPOSITSAVAILED (DEPOSIT_AVAIL_ID,CUSTOMER_ID,DEPOSIT_ID,DEPOSITED_AMOUNT,DEPOSITED_DURATION,DEPOSIT_MATURITY) values (400043,100001,1000002,50000,2,to_date('29-05-24','DD-MM-RR'));
Insert into MYBANK_APP_DEPOSITSAVAILED (DEPOSIT_AVAIL_ID,CUSTOMER_ID,DEPOSIT_ID,DEPOSITED_AMOUNT,DEPOSITED_DURATION,DEPOSIT_MATURITY) values (400045,100001,1000002,40000,1,to_date('13-04-24','DD-MM-RR'));
Insert into MYBANK_APP_DEPOSITSAVAILED (DEPOSIT_AVAIL_ID,CUSTOMER_ID,DEPOSIT_ID,DEPOSITED_AMOUNT,DEPOSITED_DURATION,DEPOSIT_MATURITY) values (400024,100001,1000001,45000,1,to_date('25-05-24','DD-MM-RR'));
Insert into MYBANK_APP_DEPOSITSAVAILED (DEPOSIT_AVAIL_ID,CUSTOMER_ID,DEPOSIT_ID,DEPOSITED_AMOUNT,DEPOSITED_DURATION,DEPOSIT_MATURITY) values (400026,100001,1000002,40000,1,to_date('29-05-24','DD-MM-RR'));
Insert into MYBANK_APP_DEPOSITSAVAILED (DEPOSIT_AVAIL_ID,CUSTOMER_ID,DEPOSIT_ID,DEPOSITED_AMOUNT,DEPOSITED_DURATION,DEPOSIT_MATURITY) values (400028,100001,1000002,40000,1,to_date('29-05-24','DD-MM-RR'));

CREATE UNIQUE INDEX "SYS_C007295" ON "MYBANK_APP_ACCOUNT" ("ACCOUNT_NUMBER");
CREATE UNIQUE INDEX "MYBANK_APP_ACCOUNT_PK" ON "MYBANK_APP_ACCOUNT" ("ACCOUNT_ID");
CREATE UNIQUE INDEX "SYS_C007292" ON "MYBANK_APP_CUSTOMER" ("USERNAME");
CREATE UNIQUE INDEX "MYBANK_APP_CUSTOMER_PK" ON "MYBANK_APP_CUSTOMER" ("CUSTOMER_ID");
CREATE UNIQUE INDEX "MYBANK_APP_DEBITCARD_PK" ON "MYBANK_APP_DEBITCARD" ("DEBITCARD_NUMBER");
CREATE UNIQUE INDEX "MYBANK_APP_DEPOSITSAVAILAB_PK" ON "MYBANK_APP_DEPOSITSAVAILABLE" ("DEPOSIT_ID");
CREATE UNIQUE INDEX "MYBANK_APP_DEPOSITSAVAILED_PK" ON "MYBANK_APP_DEPOSITSAVAILED" ("DEPOSIT_AVAIL_ID");
CREATE UNIQUE INDEX "MY_BANK_APP_INSURANCEAVAIL_PK" ON "MYBANK_APP_INSURANCEAVAILABLE" ("INSURANCE_ID");
CREATE UNIQUE INDEX "MYBANK_APP_INSURANCEAVAILE_PK" ON "MYBANK_APP_INSURANCEAVAILED" ("INSURANCE_AVAILED_ID");
CREATE UNIQUE INDEX "SYS_C007293" ON "MYBANK_APP_KYC" ("KYC_PAN");
CREATE UNIQUE INDEX "SYS_C007294" ON "MYBANK_APP_KYC" ("KYC_AADHAAR");
CREATE UNIQUE INDEX "MYBANK_APP_KYC_PK" ON "MYBANK_APP_KYC" ("KYC_NUMBER");
CREATE UNIQUE INDEX "MY_BANK_APP_LOANAVAIL_PK" ON "MYBANK_APP_LOANAVAILABLE" ("LOAN_ID");
CREATE UNIQUE INDEX "MYBANK_APP_LOANAVAILED_PK" ON "MYBANK_APP_LOANAVAILED" ("LOAN_APP_ID");
CREATE UNIQUE INDEX "MYBANK_APP_PAYEE_PK" ON "MYBANK_APP_PAYEE" ("PAYEE_ID");
CREATE UNIQUE INDEX "MYBANK_APP_TRANSACTION_PK" ON "MYBANK_APP_TRANSACTION" ("TRANSACTION_ID");

CREATE SEQUENCE MY_BANK_APP_SEQ_ACCOUNT 
    MINVALUE 1 
    MAXVALUE 9999999999999999999999999999 
    INCREMENT BY 1 
    START WITH 200021 
    CACHE 20 
    NOORDER 
    NOCYCLE;

CREATE SEQUENCE MY_BANK_APP_SEQ_CUSTOMER 
    MINVALUE 1 
    MAXVALUE 9999999999999999999999999999 
    INCREMENT BY 1 
    START WITH 100003 
    NOCACHE 
    NOORDER 
    NOCYCLE;

CREATE SEQUENCE MY_BANK_APP_SEQ_DEBITCARD 
    MINVALUE 1 
    MAXVALUE 9999999999999999999999999999 
    INCREMENT BY 1 
    START WITH 700021 
    CACHE 20 
    NOORDER 
    NOCYCLE;

CREATE SEQUENCE MY_BANK_APP_SEQ_DEPOSITSAVAIL 
    MINVALUE 1 
    MAXVALUE 9999999999999999999999999999 
    INCREMENT BY 1 
    START WITH 300041 
    CACHE 20 
    NOORDER 
    NOCYCLE;

CREATE SEQUENCE MY_BANK_APP_SEQ_DEPOSITSGIVEN 
    MINVALUE 1 
    MAXVALUE 9999999999999999999999999999 
    INCREMENT BY 1 
    START WITH 400061 
    CACHE 20 
    NOORDER 
    NOCYCLE;

CREATE SEQUENCE MY_BANK_APP_SEQ_INSURANCEAVAIL 
    MINVALUE 1 
    MAXVALUE 9999999999999999999999999999 
    INCREMENT BY 1 
    START WITH 500021 
    CACHE 20 
    NOORDER 
    NOCYCLE;

CREATE SEQUENCE MY_BANK_APP_SEQ_INSURANCEGIVEN 
    MINVALUE 1 
    MAXVALUE 9999999999999999999999999999 
    INCREMENT BY 1 
    START WITH 600021 
    CACHE 20 
    NOORDER 
    NOCYCLE;

CREATE SEQUENCE MY_BANK_APP_SEQ_KYC 
    MINVALUE 1 
    MAXVALUE 9999999999999999999999999999 
    INCREMENT BY 1 
    START WITH 200021 
    CACHE 20 
    NOORDER 
    NOCYCLE;

CREATE SEQUENCE MY_BANK_APP_SEQ_LOANAVAIL 
    MINVALUE 1 
    MAXVALUE 9999999999999999999999999999 
    INCREMENT BY 1 
    START WITH 800021 
    CACHE 20 
    NOORDER 
    NOCYCLE;

CREATE SEQUENCE MY_BANK_APP_SEQ_LOANGIVEN 
    MINVALUE 1 
    MAXVALUE 9999999999999999999999999999 
    INCREMENT BY 1 
    START WITH 900021 
    CACHE 20 
    NOORDER 
    NOCYCLE;

CREATE SEQUENCE MY_BANK_APP_SEQ_PAYEE 
    MINVALUE 1 
    MAXVALUE 9999999999999999999999999999 
    INCREMENT BY 1 
    START WITH 1100021 
    CACHE 20 
    NOORDER 
    NOCYCLE;

CREATE SEQUENCE MY_BANK_APP_SEQ_TRANSACTION 
    MINVALUE 1 
    MAXVALUE 9999999999999999999999999999 
    INCREMENT BY 1 
    START WITH 1000021 
    CACHE 20 
    NOORDER 
    NOCYCLE;

CREATE OR REPLACE TRIGGER MYBANK_APP_ACCOUNT_ID_TRG 
BEFORE INSERT ON MYBANK_APP_Account
FOR EACH ROW
BEGIN
    SELECT my_bank_app_seq_account.NEXTVAL
    INTO :NEW.account_id
    FROM dual;
END;
/

ALTER TRIGGER MYBANK_APP_ACCOUNT_ID_TRG ENABLE;

CREATE OR REPLACE TRIGGER MYBANK_APP_CUSTOMER_ID_TRG 
BEFORE INSERT ON MYBANK_APP_Customer
FOR EACH ROW
BEGIN
    SELECT my_bank_app_seq_customer.NEXTVAL
    INTO :NEW.customer_id
    FROM dual;
END;
/

ALTER TRIGGER MYBANK_APP_CUSTOMER_ID_TRG ENABLE;

CREATE OR REPLACE TRIGGER MYBANK_APP_DEBITCARD_ID_TRG 
BEFORE INSERT ON MYBANK_APP_DebitCard
FOR EACH ROW
BEGIN
    SELECT my_bank_app_seq_debitcard.NEXTVAL
    INTO :NEW.debitcard_number
    FROM dual;
END;
/

ALTER TRIGGER MYBANK_APP_DEBITCARD_ID_TRG ENABLE;

CREATE OR REPLACE TRIGGER MYBANK_APP_DEPOGIVEN_ID_TRG 
BEFORE INSERT ON MYBANK_APP_DepositsAvailed
FOR EACH ROW
BEGIN
    SELECT my_bank_app_seq_depositsgiven.NEXTVAL
    INTO :NEW.deposit_avail_id
    FROM dual;
END;
/

ALTER TRIGGER MYBANK_APP_DEPOGIVEN_ID_TRG ENABLE;

CREATE OR REPLACE TRIGGER MYBANK_APP_DEPOAVAIL_ID_TRG 
BEFORE INSERT ON MYBANK_APP_DepositsAvailable
FOR EACH ROW
BEGIN
    SELECT MY_BANK_APP_SEQ_DEPOSITSAVAIL.nextval
    INTO :NEW.deposit_id
    FROM dual;
END;
/

ALTER TRIGGER MYBANK_APP_DEPOAVAIL_ID_TRG ENABLE;

CREATE OR REPLACE TRIGGER MYBANK_APP_INSUREAVAIL_ID_TRG 
BEFORE INSERT ON MYBANK_APP_InsuranceAvailable
FOR EACH ROW
BEGIN
    SELECT my_bank_app_seq_insuranceavail.NEXTVAL
    INTO :NEW.insurance_id
    FROM dual;
END;
/

ALTER TRIGGER MYBANK_APP_INSUREAVAIL_ID_TRG ENABLE;

CREATE OR REPLACE TRIGGER MYBANK_APP_INSUREGIVEN_ID_TRG 
BEFORE INSERT ON MYBANK_APP_InsuranceAvailed
FOR EACH ROW
BEGIN
    SELECT my_bank_app_seq_insurancegiven.NEXTVAL
    INTO :NEW.insurance_availed_id
    FROM dual;
END;
/

ALTER TRIGGER MYBANK_APP_INSUREGIVEN_ID_TRG ENABLE;

CREATE OR REPLACE TRIGGER MYBANK_APP_KYC_ID_TRG 
BEFORE INSERT ON MYBANK_APP_KYC
FOR EACH ROW
BEGIN
    SELECT my_bank_app_seq_kyc.NEXTVAL
    INTO :NEW.kyc_number
    FROM dual;
END;
/

ALTER TRIGGER MYBANK_APP_KYC_ID_TRG ENABLE;

CREATE OR REPLACE TRIGGER MYBANK_APP_LOANAVAIL_ID_TRG 
BEFORE INSERT ON MYBANK_APP_LoanAvailable
FOR EACH ROW
BEGIN
    SELECT my_bank_app_seq_loanavail.NEXTVAL
    INTO :NEW.loan_id
    FROM dual;
END;
/

ALTER TRIGGER MYBANK_APP_LOANAVAIL_ID_TRG ENABLE;

CREATE OR REPLACE TRIGGER MYBANK_APP_LOANGIVEN_ID_TRG 
BEFORE INSERT ON MYBANK_APP_LoanAvailed
FOR EACH ROW
BEGIN
    SELECT my_bank_app_seq_loangiven.NEXTVAL
    INTO :NEW.loan_app_id
    FROM dual;
END;
/

ALTER TRIGGER MYBANK_APP_LOANGIVEN_ID_TRG ENABLE;

CREATE OR REPLACE TRIGGER MYBANK_APP_PAYEE_ID_TRG 
BEFORE INSERT ON MYBANK_APP_Payee
FOR EACH ROW
BEGIN
    SELECT my_bank_app_seq_payee.NEXTVAL
    INTO :NEW.payee_id
    FROM dual;
END;
/

ALTER TRIGGER MYBANK_APP_PAYEE_ID_TRG ENABLE;

CREATE OR REPLACE TRIGGER MYBANK_APP_TRANSACTION_ID_TRG 
BEFORE INSERT ON MYBANK_APP_Transaction
FOR EACH ROW
BEGIN
    SELECT my_bank_app_seq_transaction.NEXTVAL
    INTO :NEW.transaction_id
    FROM dual;
END;
/

ALTER TRIGGER MYBANK_APP_TRANSACTION_ID_TRG ENABLE;

-- Constraints for Table MYBANK_APP_ACCOUNT
ALTER TABLE MYBANK_APP_ACCOUNT MODIFY (ACCOUNT_STATUS NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_ACCOUNT MODIFY (ACCOUNT_NUMBER NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_ACCOUNT MODIFY (ACCOUNT_TYPE NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_ACCOUNT MODIFY (CUSTOMER_ID NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_ACCOUNT ADD CONSTRAINT MYBANK_APP_ACCOUNT_PK PRIMARY KEY (ACCOUNT_ID);
ALTER TABLE MYBANK_APP_ACCOUNT MODIFY (ACCOUNT_ID NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_ACCOUNT ADD UNIQUE (ACCOUNT_NUMBER);

-- Constraints for Table MYBANK_APP_CUSTOMER
ALTER TABLE MYBANK_APP_CUSTOMER MODIFY (CUSTOMER_STATUS NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_CUSTOMER MODIFY (CUSTOMER_ADDRESS NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_CUSTOMER MODIFY (CUSTOMER_NAME NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_CUSTOMER ADD CONSTRAINT MYBANK_APP_CUSTOMER_PK PRIMARY KEY (CUSTOMER_ID);
ALTER TABLE MYBANK_APP_CUSTOMER MODIFY (CUSTOMER_ID NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_CUSTOMER ADD UNIQUE (USERNAME);
ALTER TABLE MYBANK_APP_CUSTOMER MODIFY (PASSWORD NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_CUSTOMER MODIFY (USERNAME NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_CUSTOMER MODIFY (CUSTOMER_CONTACT NOT NULL ENABLE);

-- Constraints for Table MYBANK_APP_DEBITCARD
ALTER TABLE MYBANK_APP_DEBITCARD ADD CONSTRAINT MYBANK_APP_DEBITCARD_PK PRIMARY KEY (DEBITCARD_NUMBER);
ALTER TABLE MYBANK_APP_DEBITCARD MODIFY (DEBITCARD_NUMBER NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_DEBITCARD MODIFY (DEBITCARD_INTERNATIONAL_LIMIT NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_DEBITCARD MODIFY (DEBITCARD_DOMESTIC_LIMIT NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_DEBITCARD MODIFY (DEBITCARD_STATUS NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_DEBITCARD MODIFY (DEBITCARD_EXPIRY NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_DEBITCARD MODIFY (DEBITCARD_PIN NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_DEBITCARD MODIFY (DEBITCARD_CVV NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_DEBITCARD MODIFY (CUSTOMER_ID NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_DEBITCARD MODIFY (ACCOUNT_NUMBER NOT NULL ENABLE);

-- Constraints for Table MYBANK_APP_DEPOSITSAVAILABLE
ALTER TABLE MYBANK_APP_DEPOSITSAVAILABLE ADD CONSTRAINT MYBANK_APP_DEPOSITSAVAILAB_PK PRIMARY KEY (DEPOSIT_ID);
ALTER TABLE MYBANK_APP_DEPOSITSAVAILABLE MODIFY (DEPOSIT_ID NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_DEPOSITSAVAILABLE MODIFY (DEPOSIT_DESCRIPTION NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_DEPOSITSAVAILABLE MODIFY (DEPOSIT_TYPE NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_DEPOSITSAVAILABLE MODIFY (DEPOSIT_ROI NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_DEPOSITSAVAILABLE MODIFY (DEPOSIT_NAME NOT NULL ENABLE);

-- Constraints for Table MYBANK_APP_DEPOSITSAVAILED
ALTER TABLE MYBANK_APP_DEPOSITSAVAILED ADD CONSTRAINT MYBANK_APP_DEPOSITSAVAILED_PK PRIMARY KEY (DEPOSIT_AVAIL_ID);
ALTER TABLE MYBANK_APP_DEPOSITSAVAILED MODIFY (DEPOSIT_AVAIL_ID NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_DEPOSITSAVAILED MODIFY (DEPOSIT_MATURITY NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_DEPOSITSAVAILED MODIFY (DEPOSITED_DURATION NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_DEPOSITSAVAILED MODIFY (DEPOSITED_AMOUNT NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_DEPOSITSAVAILED MODIFY (DEPOSIT_ID NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_DEPOSITSAVAILED MODIFY (CUSTOMER_ID NOT NULL ENABLE);

-- Constraints for Table MYBANK_APP_INSURANCEAVAILABLE
ALTER TABLE MYBANK_APP_INSURANCEAVAILABLE ADD CONSTRAINT MY_BANK_APP_INSURANCEAVAIL_PK PRIMARY KEY (INSURANCE_ID);
ALTER TABLE MYBANK_APP_INSURANCEAVAILABLE MODIFY (INSURANCE_LIFETIME NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_INSURANCEAVAILABLE MODIFY (INSURANCE_KEY_BENEFITS NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_INSURANCEAVAILABLE MODIFY (INSURANCE_NAME NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_INSURANCEAVAILABLE MODIFY (INSURANCE_TYPE NOT NULL ENABLE);

-- Constraints for Table MYBANK_APP_INSURANCEAVAILED
ALTER TABLE MYBANK_APP_INSURANCEAVAILED ADD CONSTRAINT MYBANK_APP_INSURANCEAVAILE_PK PRIMARY KEY (INSURANCE_AVAILED_ID);
ALTER TABLE MYBANK_APP_INSURANCEAVAILED MODIFY (INSURANCE_AVAILED_ID NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_INSURANCEAVAILED MODIFY (INSURANCE_PREMIUM NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_INSURANCEAVAILED MODIFY (INSURANCE_COVERAGE NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_INSURANCEAVAILED MODIFY (INSURANCE_ID NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_INSURANCEAVAILED MODIFY (CUSTOMER_ID NOT NULL ENABLE);

-- Constraints for Table MYBANK_APP_KYC
ALTER TABLE MYBANK_APP_KYC ADD CONSTRAINT MYBANK_APP_KYC_PK PRIMARY KEY (KYC_NUMBER);
ALTER TABLE MYBANK_APP_KYC MODIFY (KYC_NUMBER NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_KYC ADD UNIQUE (KYC_AADHAAR);
ALTER TABLE MYBANK_APP_KYC ADD UNIQUE (KYC_PAN);
ALTER TABLE MYBANK_APP_KYC MODIFY (KYC_STATUS NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_KYC MODIFY (KYC_AADHAAR NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_KYC MODIFY (KYC_PAN NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_KYC MODIFY (CUSTOMER_ID NOT NULL ENABLE);

-- Constraints for Table MYBANK_APP_LOANAVAILABLE
ALTER TABLE MYBANK_APP_LOANAVAILABLE ADD CONSTRAINT MY_BANK_APP_LOANAVAIL_PK PRIMARY KEY (LOAN_ID);
ALTER TABLE MYBANK_APP_LOANAVAILABLE MODIFY (LOAN_ROI NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_LOANAVAILABLE MODIFY (LOAN_DESCRIPTION NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_LOANAVAILABLE MODIFY (LOAN_NAME NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_LOANAVAILABLE MODIFY (LOAN_TYPE NOT NULL ENABLE);

-- Constraints for Table MYBANK_APP_LOANAVAILED
ALTER TABLE MYBANK_APP_LOANAVAILED ADD CONSTRAINT MYBANK_APP_LOANAVAILED_PK PRIMARY KEY (LOAN_APP_ID);
ALTER TABLE MYBANK_APP_LOANAVAILED MODIFY (LOAN_APP_ID NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_LOANAVAILED MODIFY (LOAN_TENURE NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_LOANAVAILED MODIFY (LOAN_EMI NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_LOANAVAILED MODIFY (LOAN_AMOUNT NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_LOANAVAILED MODIFY (LOAN_ID NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_LOANAVAILED MODIFY (CUSTOMER_ID NOT NULL ENABLE);

-- Constraints for Table MYBANK_APP_PAYEE
ALTER TABLE MYBANK_APP_PAYEE ADD CONSTRAINT MYBANK_APP_PAYEE_PK PRIMARY KEY (PAYEE_ID);
ALTER TABLE MYBANK_APP_PAYEE MODIFY (PAYEE_ID NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_PAYEE MODIFY (PAYEE_NAME NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_PAYEE MODIFY (ACCOUNT_NUMBER NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_PAYEE MODIFY (CUSTOMER_ID NOT NULL ENABLE);

-- Constraints for Table MYBANK_APP_TRANSACTION
ALTER TABLE MYBANK_APP_TRANSACTION ADD CONSTRAINT MYBANK_APP_TRANSACTION_PK PRIMARY KEY (TRANSACTION_ID);
ALTER TABLE MYBANK_APP_TRANSACTION MODIFY (TRANSACTION_ID NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_TRANSACTION MODIFY (TRANSACTION_STATUS NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_TRANSACTION MODIFY (TRANSACTION_AMOUNT NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_TRANSACTION MODIFY (TRANSACTION_DATE NOT NULL ENABLE);
ALTER TABLE MYBANK_APP_TRANSACTION MODIFY (TRANSACTION_TYPE NOT NULL ENABLE);

-- Ref Constraints for Table MYBANK_APP_ACCOUNT
ALTER TABLE MYBANK_APP_ACCOUNT ADD CONSTRAINT FK_ACCOUNT_CUSTOMER FOREIGN KEY (CUSTOMER_ID)
    REFERENCES MYBANK_APP_CUSTOMER (CUSTOMER_ID) ON DELETE CASCADE;

-- Ref Constraints for Table MYBANK_APP_DEBITCARD
ALTER TABLE MYBANK_APP_DEBITCARD ADD CONSTRAINT FK_DEBITCARD_CUSTOMER FOREIGN KEY (CUSTOMER_ID)
    REFERENCES MYBANK_APP_CUSTOMER (CUSTOMER_ID) ON DELETE CASCADE;
ALTER TABLE MYBANK_APP_DEBITCARD ADD CONSTRAINT MYBANK_APP_ACCOUNT_FK FOREIGN KEY (ACCOUNT_NUMBER)
    REFERENCES MYBANK_APP_ACCOUNT (ACCOUNT_NUMBER) ON DELETE CASCADE;

-- Ref Constraints for Table MYBANK_APP_DEPOSITSAVAILED
ALTER TABLE MYBANK_APP_DEPOSITSAVAILED ADD CONSTRAINT FK_DEPOSGIVEN_DEPOAVAILABLE FOREIGN KEY (DEPOSIT_ID)
    REFERENCES MYBANK_APP_DEPOSITSAVAILABLE (DEPOSIT_ID) ON DELETE CASCADE;
ALTER TABLE MYBANK_APP_DEPOSITSAVAILED ADD CONSTRAINT FK_DEPOSITSAVAILED_CUSTOMER FOREIGN KEY (CUSTOMER_ID)
    REFERENCES MYBANK_APP_CUSTOMER (CUSTOMER_ID) ON DELETE CASCADE;

-- Ref Constraints for Table MYBANK_APP_INSURANCEAVAILED
ALTER TABLE MYBANK_APP_INSURANCEAVAILED ADD CONSTRAINT FK_INSURANCEAVAILED_CUSTOMER FOREIGN KEY (CUSTOMER_ID)
    REFERENCES MYBANK_APP_CUSTOMER (CUSTOMER_ID) ON DELETE CASCADE;
ALTER TABLE MYBANK_APP_INSURANCEAVAILED ADD CONSTRAINT FK_INSUREGIVEN_INSUREAVAILABLE FOREIGN KEY (INSURANCE_ID)
    REFERENCES MYBANK_APP_INSURANCEAVAILABLE (INSURANCE_ID) ON DELETE CASCADE;

-- Ref Constraints for Table MYBANK_APP_KYC
ALTER TABLE MYBANK_APP_KYC ADD CONSTRAINT FK_KYC_CUSTOMER FOREIGN KEY (CUSTOMER_ID)
    REFERENCES MYBANK_APP_CUSTOMER (CUSTOMER_ID) ON DELETE CASCADE;

-- Ref Constraints for Table MYBANK_APP_LOANAVAILED
ALTER TABLE MYBANK_APP_LOANAVAILED ADD CONSTRAINT FK_LOANAVAILED_CUSTOMER FOREIGN KEY (CUSTOMER_ID)
    REFERENCES MYBANK_APP_CUSTOMER (CUSTOMER_ID) ON DELETE CASCADE;
ALTER TABLE MYBANK_APP_LOANAVAILED ADD CONSTRAINT FK_LOANAVAILED_LOANAVAILABLE FOREIGN KEY (LOAN_ID)
    REFERENCES MYBANK_APP_LOANAVAILABLE (LOAN_ID) ON DELETE CASCADE;

-- Ref Constraints for Table MYBANK_APP_PAYEE
ALTER TABLE MYBANK_APP_PAYEE ADD CONSTRAINT FK_PAYEE_CUSTOMER FOREIGN KEY (CUSTOMER_ID)
    REFERENCES MYBANK_APP_CUSTOMER (CUSTOMER_ID) ON DELETE CASCADE;
ALTER TABLE MYBANK_APP_PAYEE ADD CONSTRAINT MYBANK_APP_ACCOUNT FOREIGN KEY (ACCOUNT_NUMBER)
    REFERENCES MYBANK_APP_ACCOUNT (ACCOUNT_NUMBER) ON DELETE CASCADE;

-- Ref Constraints for Table MYBANK_APP_TRANSACTION
ALTER TABLE MYBANK_APP_TRANSACTION ADD CONSTRAINT MYBANK_ACCOUNT_TO FOREIGN KEY (TRANSACTION_FROM)
    REFERENCES MYBANK_APP_ACCOUNT (ACCOUNT_NUMBER) ON DELETE CASCADE;
ALTER TABLE MYBANK_APP_TRANSACTION ADD CONSTRAINT MYBANK_APP_TRANSACTION_FK1 FOREIGN KEY (TRANSACTION_TO)
    REFERENCES MYBANK_APP_ACCOUNT (ACCOUNT_NUMBER) ON DELETE CASCADE;


create or replace PROCEDURE avail_deposits (
    p_customer_id      NUMBER,
    p_deposit_id       NUMBER,
    p_deposit_amount   NUMBER,
    p_deposit_duration INT,
    p_deposit_maturity DATE
) AS
    p_result  VARCHAR(25);
    v_ack             NUMBER;
    v_customer_status VARCHAR(25);
BEGIN
    -- Check customer status
    SELECT CUSTOMER_STATUS INTO v_customer_status FROM MYBANK_APP_CUSTOMER WHERE CUSTOMER_ID = p_customer_id;

    IF v_customer_status ='active' THEN
        -- Insert data into MYBANK_APP_DEPOSITSAVAILED table
        INSERT INTO MYBANK_APP_DEPOSITSAVAILED (
            deposit_avail_id,
            customer_id,
            deposit_id,
            deposited_amount,
            deposited_duration,
            deposit_maturity
        ) VALUES (
            MY_BANK_APP_SEQ_DEPOSITSGIVEN.nextval,
            p_customer_id,
            p_deposit_id,
            p_deposit_amount,
            p_deposit_duration,
            p_deposit_maturity
        );

        -- Check if the insertion was successful
        v_ack := SQL%ROWCOUNT;

        IF v_ack > 0 THEN
            p_result := 'Success';
        ELSE
            p_result := 'Fail';
            RAISE_APPLICATION_ERROR(-20001, 'Failed to insert data into MYBANK_APP_DEPOSITSAVAILED table');
        END IF;
    ELSE
        -- Customer is inactive, raise an application error
        RAISE_APPLICATION_ERROR(-20002, 'Customer is inactive');
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        -- Handle exceptions
        p_result := 'Fail';
            RAISE_APPLICATION_ERROR(-20002, SQLERRM);
END avail_deposits;

commit;
