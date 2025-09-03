
use JavaTraining;
-- Drop tables if already exist (for resetting DB)
DROP TABLE IF EXISTS SwipeRecords;
DROP TABLE IF EXISTS Card;
DROP TABLE IF EXISTS Station;
DROP TABLE IF EXISTS User;

-- 1. User table
CREATE TABLE User (
    userId INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL
);

-- 2. Card table
CREATE TABLE Card (
    cardNo INT AUTO_INCREMENT PRIMARY KEY,
    balance DOUBLE NOT NULL,
    userId INT NOT NULL,
    FOREIGN KEY (userId) REFERENCES User(userId) ON DELETE CASCADE
);

-- 3. Station table
CREATE TABLE Station (
    stationId INT AUTO_INCREMENT PRIMARY KEY,
    stationName VARCHAR(50) NOT NULL UNIQUE
);

-- 4. SwipeRecords table
CREATE TABLE SwipeRecords (
    recordId INT AUTO_INCREMENT PRIMARY KEY,
    cardNo INT NOT NULL,
    fareDeducted DOUBLE NOT NULL,
    start INT NOT NULL,
    end INT NOT NULL,
    startTime TIMESTAMP NOT NULL,
    endTime TIMESTAMP,
    date DATE NOT NULL,
    FOREIGN KEY (cardNo) REFERENCES Card(cardNo) ON DELETE CASCADE,
    FOREIGN KEY (start) REFERENCES Station(stationId),
    FOREIGN KEY (end) REFERENCES Station(stationId)
);

CREATE TABLE transactions (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    card_no INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (card_no) REFERENCES card(cardNo)
);
-- Insert Users
INSERT INTO User (name, email) VALUES 
('Alice', 'alice@example.com'),
('Bob', 'bob@example.com');

-- Insert Cards
INSERT INTO Card (balance, userId) VALUES
(200.00, 1),
(150.00, 2);

-- Insert Stations
INSERT INTO Station (stationName) VALUES
('Central'),
('Park Street'),
('City Mall'),
('Airport'),
('Tech Hub');

-- Insert SwipeRecords
INSERT INTO SwipeRecords (cardNo, fareDeducted, start, end, startTime, endTime, date)
VALUES
(1, 15.0, 1, 3, '2025-08-31 08:30:00', '2025-08-31 09:00:00', '2025-08-31'),
(2, 20.0, 2, 5, '2025-08-30 10:00:00', '2025-08-30 10:40:00', '2025-08-30');

