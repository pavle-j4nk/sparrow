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




