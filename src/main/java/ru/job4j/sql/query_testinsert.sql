create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('Фен', 1999.99);
insert into devices(name, price) values ('Мультиварка', 4999.99);
insert into devices(name, price) values ('Телевизор', 15555);
insert into devices(name, price) values ('Наушники', 3999.99);
insert into devices(name, price) values ('Чайник', 2500.90);
insert into devices(name, price) values ('Телевизор', 15555);

insert into people(name) values ('Аня');
insert into people(name) values ('Ваня');
insert into people(name) values ('Боря');

insert into devices_people(device_id, people_id) values (1, 1);
insert into devices_people(device_id, people_id) values (2, 1);
insert into devices_people(device_id, people_id) values (3, 1);
insert into devices_people(device_id, people_id) values (1, 2);
insert into devices_people(device_id, people_id) values (2, 2);
insert into devices_people(device_id, people_id) values (3, 1);
insert into devices_people(device_id, people_id) values (3, 3);