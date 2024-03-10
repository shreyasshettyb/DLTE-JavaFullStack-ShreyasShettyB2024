CREATE TABLE Before_norm (
    username VARCHAR2(255),
    upi_address VARCHAR2(255),
    mobilenumber Number,
    email VARCHAR2(255),
    wallet_type VARCHAR2(50),
    recharged_date DATE,
    recharged_provider VARCHAR2(100),
    recharged_to VARCHAR2(255),
    recharged_amount Decimal(10,2)
);

--After Normalization 

CREATE TABLE User_norm (
    username VARCHAR2(255),
    mobilenumber NUMBER PRIMARY KEY,
    email VARCHAR2(255)
);

CREATE TABLE Wallet_After_norm (
    upi VARCHAR2(255) PRIMARY KEY,
    wallet_type VARCHAR(255),
    mobilenumber Number
    FOREIGN KEY (mobilenumber) REFERENCES User_norm(mobilenumber)
);

CREATE TABLE Recharge_After_norm (
    upi VARCHAR2(255) PRIMARY KEY,
    recharged_amount DECIMAL(10, 2),
    recharged_date DATE,
    recharged_provider VARCHAR(255),
    FOREIGN KEY (upi) REFERENCES Wallet_After_norm(upi)
);
