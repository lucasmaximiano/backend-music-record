CREATE TABLE music_record.sale_item (
  id INT AUTO_INCREMENT PRIMARY KEY,
  sale_id INT NOT NULL,
  disc_id INT NOT NULL,
  name VARCHAR(255) NOT NULL,
  quantity INT(11) NOT NULL,
  price DECIMAL NOT NULL,
  total_price DECIMAL NOT NULL,
  cash_back_value DECIMAL NOT NULL,
  CONSTRAINT FK_SALE_ITEM_SALE FOREIGN KEY (sale_id) REFERENCES sale (id),
  CONSTRAINT FK_SALE_ITEM_DISC FOREIGN KEY (disc_id) REFERENCES disc (id)
);