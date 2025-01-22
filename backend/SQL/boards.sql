CREATE TABLE boards (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id String NOT NULL,
    title VARCHAR(50) NOT NULL,
    create_at DATETIME NOT NULL default current_time,
    update_at DATETIME NOT NULL default current_time,
    content Text,
    view INT DEFAULT 0,
    like_count INT DEFAULT 0,
    CONSTRAINT fk_member FOREIGN KEY (user_id) REFERENCES member(login_id) ON DELETE CASCADE
);
