CREATE TABLE music_record.sale (
  id INT AUTO_INCREMENT PRIMARY KEY,
  customer_name VARCHAR(255) NOT NULL,
  customer_email VARCHAR(255) NOT NULL,
  total_price DECIMAL(10,2) NOT NULL,
  cash_back_total_value DECIMAL(10,2) NOT NULL,
  created_date DATETIME NOT NULL
);