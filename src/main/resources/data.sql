CREATE TABLE nisum_user
(
    userIdentifier VARCHAR(255) PRIMARY KEY,
    name           VARCHAR(255),
    email          VARCHAR(255),
    password       VARCHAR(255),
    created        BIGINT,
    modified       BIGINT,
    lastLogin      BIGINT,
    token          VARCHAR(255),
    isActive       BOOLEAN
);

CREATE TABLE phone
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    number      VARCHAR(255),
    cityCode    VARCHAR(255),
    countryCode VARCHAR(255),
    userId      VARCHAR(255),
    FOREIGN KEY (userId) REFERENCES nisum_user (userIdentifier) ON DELETE CASCADE
);



