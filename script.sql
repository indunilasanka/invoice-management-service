Create DATABASE invoice_data_db;

Use invoice_data_db;

CREATE TABLE `invoices` (
    `id` int NOT NULL AUTO_INCREMENT,
    `customer` varchar(25) DEFAULT NULL,
    `date` date NOT NULL,
    `gross_amount` decimal(13,3) NOT NULL,
    `net_amount` decimal(13,4) NOT NULL,
    `discount` decimal(13,5) NOT NULL,
    `status` enum('PENDING','APPROVED') NOT NULL DEFAULT 'PENDING',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO invoice_data_db.invoices (customer, `date`, gross_amount, net_amount, discount, status) VALUES('ABC-Customer12', '2021-11-07', 102.312, 99.1930, 3.11900, 'PENDING');
INSERT INTO invoice_data_db.invoices (customer, `date`, gross_amount, net_amount, discount, status) VALUES('ABC-Customer1', '2021-11-07', 84.312, 84.3120, 0.00000, 'PENDING');
INSERT INTO invoice_data_db.invoices (customer, `date`, gross_amount, net_amount, discount, status) VALUES('ABC-Customer2', '2021-11-06', 99.312, 88.1930, 11.11900, 'APPROVED');
INSERT INTO invoice_data_db.invoices (customer, `date`, gross_amount, net_amount, discount, status) VALUES('ABC-Customer4', '2021-11-07', 152.312, 144.1240, 8.18800, 'PENDING');
INSERT INTO invoice_data_db.invoices (customer, `date`, gross_amount, net_amount, discount, status) VALUES('ABC-Customer5', '2021-11-06', 312.320, 299.1880, 13.13200, 'APPROVED');
INSERT INTO invoice_data_db.invoices (customer, `date`, gross_amount, net_amount, discount, status) VALUES('ABC-Customer65', '2021-11-07', 224.210, 220.8900, 3.32000, 'PENDING');
INSERT INTO invoice_data_db.invoices (customer, `date`, gross_amount, net_amount, discount, status) VALUES('ABC-Customer8', '2021-11-06', 342.312, 321.1930, 21.11900, 'PENDING');
INSERT INTO invoice_data_db.invoices (customer, `date`, gross_amount, net_amount, discount, status) VALUES('ABC-Customer34', '2021-11-06', 348.120, 348.1000, 0.02000, 'PENDING');
INSERT INTO invoice_data_db.invoices (customer, `date`, gross_amount, net_amount, discount, status) VALUES('ABC-Customer5', '2021-11-07', 44.312, 42.1930, 2.11900, 'PENDING');
INSERT INTO invoice_data_db.invoices (customer, `date`, gross_amount, net_amount, discount, status) VALUES('ABC-Customer34', '2021-11-07', 82.122, 80.1930, 1.92900, 'PENDING');
INSERT INTO invoice_data_db.invoices (customer, `date`, gross_amount, net_amount, discount, status) VALUES('ABC-Customer44', '2021-11-07', 102.312, 99.1930, 3.11900, 'PENDING');
INSERT INTO invoice_data_db.invoices (customer, `date`, gross_amount, net_amount, discount, status) VALUES('ABC-Customer62', '2021-11-07', 84.312, 84.3120, 0.00000, 'PENDING');
INSERT INTO invoice_data_db.invoices (customer, `date`, gross_amount, net_amount, discount, status) VALUES('ABC-Customer82', '2021-11-06', 99.312, 88.1930, 11.11900, 'PENDING');
INSERT INTO invoice_data_db.invoices (customer, `date`, gross_amount, net_amount, discount, status) VALUES('ABC-Customer24', '2021-11-07', 152.312, 144.1240, 8.18800, 'PENDING');
INSERT INTO invoice_data_db.invoices (customer, `date`, gross_amount, net_amount, discount, status) VALUES('ABC-Customer6', '2021-11-06', 312.320, 299.1880, 13.13200, 'PENDING');
INSERT INTO invoice_data_db.invoices (customer, `date`, gross_amount, net_amount, discount, status) VALUES('ABC-Customer42', '2021-11-07', 224.210, 220.8900, 3.32000, 'PENDING');
INSERT INTO invoice_data_db.invoices (customer, `date`, gross_amount, net_amount, discount, status) VALUES('ABC-Customer56', '2021-11-06', 342.312, 321.1930, 21.11900, 'PENDING');
INSERT INTO invoice_data_db.invoices (customer, `date`, gross_amount, net_amount, discount, status) VALUES('ABC-Customer82', '2021-11-06', 348.120, 348.1000, 0.02000, 'PENDING');
INSERT INTO invoice_data_db.invoices (customer, `date`, gross_amount, net_amount, discount, status) VALUES('ABC-Customer9', '2021-11-07', 44.312, 42.1930, 2.11900, 'APPROVED');
INSERT INTO invoice_data_db.invoices (customer, `date`, gross_amount, net_amount, discount, status) VALUES('ABC-Customer3', '2021-11-07', 82.122, 80.1930, 1.92900, 'PENDING');
