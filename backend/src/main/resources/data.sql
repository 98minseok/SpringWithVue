CREATE TABLE MEMBER (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20),
    nickname VARCHAR(30),
    login_id VARCHAR(30) UNIQUE,
    password VARCHAR(255),
    email VARCHAR(50)
);

INSERT INTO member (name, nickname) VALUES ('Alice', 'Wonderland');
INSERT INTO member (name, nickname) VALUES ('Bob', 'Builder');