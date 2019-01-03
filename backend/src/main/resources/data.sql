insert into hotel (id, name, description) values (1, 'Plaza', 'New York most famous hotel');
insert into hotel (id, name, description) values (2, 'Ritz', 'Paris most famous hotel');
insert into hotel (id, name, description) values (3, 'Vojvodina', 'Not Serbian most famous hotel');
insert into hotel (id, name, description) values (4, 'Hilton', 'Paris he he');

-- Rooms for Hotel Plaza
insert into room (id, name, beds_number, hotel_id) values (1, 'A1', 2 , 1);
insert into room (id, name, beds_number, hotel_id) values (2, 'A2', 2 , 1);
insert into room (id, name, beds_number, hotel_id) values (3, 'A3', 3 , 1);
insert into room (id, name, beds_number, hotel_id) values (4, 'A4', 3 , 1);
insert into room (id, name, beds_number, hotel_id) values (5, 'A5', 5 , 1);
insert into room (id, name, beds_number, hotel_id) values (6, 'A6', 5 , 1);

-- Rooms for other hotels
insert into room (id, name, beds_number, hotel_id) values (7, '1', 2 , 2);
insert into room (id, name, beds_number, hotel_id) values (8, '2', 2 , 3);
insert into room (id, name, beds_number, hotel_id) values (9, '33', 3 , 4);
insert into room (id, name, beds_number, hotel_id) values (10, '44', 3 , 4);
insert into room (id, name, beds_number, hotel_id) values (11, '55', 5 , 3);
insert into room (id, name, beds_number, hotel_id) values (12, '16', 5 , 2);

-- Extra Services
 insert into extra_service (name) values ('TRANSPORT_FROM_AIRPORT');
 insert into extra_service (name) values ('TRANSPORT_TO_AIRPORT');
 insert into extra_service (name) values ('PARKING');
 insert into extra_service (name) values ('POOL');
 insert into extra_service (name) values ('RESTAURANT');
 insert into extra_service (name) values ('ROOM_SERVICE');
 insert into extra_service (name) values ('WELLNESS');
 insert into extra_service (name) values ('SPA');
 insert into extra_service (name) values ('WIRELESS');
 insert into extra_service (name) values ('AIR_CONDITIONING');

-- Hotel services
insert into hotel_services (id, hotel_id, extra_service_name, price) values (1, 1,'TRANSPORT_FROM_AIRPORT',20);
insert into hotel_services (id, hotel_id, extra_service_name, price) values (2, 1,'TRANSPORT_TO_AIRPORT',20);
insert into hotel_services (id, hotel_id, extra_service_name, price) values (3, 1,'RESTAURANT',6);
insert into hotel_services (id, hotel_id, extra_service_name, price) values (4, 1,'WIRELESS',1);
insert into hotel_services (id, hotel_id, extra_service_name, price) values (5, 1,'AIR_CONDITIONING',5);
insert into hotel_services (id, hotel_id, extra_service_name, price) values (6, 2,'WELLNESS',17);
insert into hotel_services (id, hotel_id, extra_service_name, price) values (7, 2,'SPA',9);
insert into hotel_services (id, hotel_id, extra_service_name, price) values (8, 3,'RESTAURANT',7);
insert into hotel_services (id, hotel_id, extra_service_name, price) values (9, 4,'WIRELESS',2);
insert into hotel_services (id, hotel_id, extra_service_name, price) values (10, 4,'AIR_CONDITIONING',0);




