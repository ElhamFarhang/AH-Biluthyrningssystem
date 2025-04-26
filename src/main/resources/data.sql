
--------------------- Elham - customersData --------------
INSERT INTO CUSTOMER (first_name, last_name, personal_number, address, email, phone_number) VALUES ('Anna', 'Svensson', '19850101-1234', 'Skåne', 'Anna@mail.com', '0728686636');
INSERT INTO CUSTOMER (first_name, last_name, personal_number, address, email, phone_number) VALUES ('Erik', 'Johansson', '19900215-5678', 'Sundsvall', 'Erik@mail.com', '0733654289');
INSERT INTO CUSTOMER (first_name, last_name, personal_number, address, email, phone_number) VALUES ('Maria', 'Lindberg', '19751230-9101', 'Gävle', 'Maria@mail.com', null);
INSERT INTO CUSTOMER (first_name, last_name, personal_number, address, email, phone_number) VALUES ('Johan', 'Karlsson', '19881122-3456', 'Malmö', 'Johan@mail.com', null );
INSERT INTO CUSTOMER (first_name, last_name, personal_number, address, email, phone_number) VALUES ('Elin', 'Andersson', '19950505-7890', 'Stockholm', 'Elin@mail.com', '0832565850');
----order---
INSERT INTO ORDER (ACTIVE, CANCELED, CUSTOMER_ID, TOTAL_COST, DATE_CREATED, DATE_END, DATE_START) VALUES (true, false, 2, 2000, 2025-01-01, 2025-11-01,2025-07-01)
