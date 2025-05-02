
--------------------- Elham - customersData --------------
INSERT INTO CUSTOMER (first_name, last_name, personal_number, address, email, phone_number) VALUES ('Anna', 'Svensson', '19850101-1234', 'Skåne', 'Anna@mail.com', '0728686636');
INSERT INTO CUSTOMER (first_name, last_name, personal_number, address, email, phone_number) VALUES ('Erik', 'Johansson', '19900215-5678', 'Sundsvall', 'Erik@mail.com', '0733654289');
INSERT INTO CUSTOMER (first_name, last_name, personal_number, address, email, phone_number) VALUES ('Maria', 'Lindberg', '19751230-9101', 'Gävle', 'Maria@mail.com', null);
INSERT INTO CUSTOMER (first_name, last_name, personal_number, address, email, phone_number) VALUES ('Johan', 'Karlsson', '19881122-3456', 'Malmö', 'Johan@mail.com', null );
INSERT INTO CUSTOMER (first_name, last_name, personal_number, address, email, phone_number) VALUES ('Elin', 'Andersson', '19950505-7890', 'Stockholm', 'Elin@mail.com', '0832565850');
--------------------- Anna---ORDERS---------------
INSERT INTO ORDERS (DATE_CREATED, DATE_START, DATE_END, CUSTOMER_ID, CAR_ID, CANCELED, TOTAL_COST)  VALUES ('2025-01-01', '2025-02-01','2025-07-01',1, null,  false, 2000);
INSERT INTO ORDERS (DATE_CREATED, DATE_START, DATE_END, CUSTOMER_ID, CAR_ID, CANCELED, TOTAL_COST)  VALUES ('2025-01-01', '2025-02-01','2025-05-01',2, null, true, 4000);
INSERT INTO ORDERS (DATE_CREATED, DATE_START, DATE_END, CUSTOMER_ID, CAR_ID, CANCELED, TOTAL_COST)  VALUES ('2025-02-01', '2025-06-01','2025-07-01',1, null, false, 2000);
INSERT INTO ORDERS (DATE_CREATED, DATE_START, DATE_END, CUSTOMER_ID, CAR_ID, CANCELED, TOTAL_COST)  VALUES ('2025-01-01', '2025-02-01','2025-04-01',3, null,  false, 7000);
INSERT INTO ORDERS (DATE_CREATED, DATE_START, DATE_END, CUSTOMER_ID, CAR_ID, CANCELED, TOTAL_COST)  VALUES ('2025-01-01', '2025-02-01','2025-04-01',2, null,  false, 2000);
INSERT INTO ORDERS (DATE_CREATED, DATE_START, DATE_END, CUSTOMER_ID, CAR_ID, CANCELED, TOTAL_COST)  VALUES ('2025-01-01', '2025-02-01','2025-04-01',4, null,  true, 6400);
INSERT INTO ORDERS (DATE_CREATED, DATE_START, DATE_END, CUSTOMER_ID, CAR_ID, CANCELED, TOTAL_COST)  VALUES ('2025-01-01', '2025-02-01','2025-07-01',2, null,  true, 1000);
INSERT INTO ORDERS (DATE_CREATED, DATE_START, DATE_END, CUSTOMER_ID, CAR_ID, CANCELED, TOTAL_COST)  VALUES ('2025-01-01', '2025-02-01','2025-07-01',5, null,  false, 2463);