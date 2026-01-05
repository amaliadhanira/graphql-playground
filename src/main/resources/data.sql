DROP TABLE IF EXISTS customer;

CREATE TABLE customer(
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(50),
    age INT,
    city VARCHAR(50)
);

insert into customer(name, age, city)
values
    ('Parjo', 10, 'Jakarta'),
    ('Sumiati', 15, 'Solo'),
    ('Susi', 20, 'Bandung'),
    ('Budi', 30, 'Yogyakarta');