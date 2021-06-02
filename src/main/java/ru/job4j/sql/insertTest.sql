create table positions(
    id serial primary key,
    name varchar(255),
    guarentee int,
    serial_numb int,
    seria varchar(255),
    color varchar(255),
    country varchar(255),
    number int,
    price int
);

create table products(
    id serial primary key,
    name varchar(255),
    positions_id int references positions(id) unique
);

insert into positions(name, guarentee, serial_numb, seria, color, country, number, price) values ('Sony', 1, 111101, 'ZTE', 'Black', 'Janan', 123456, 49990);
insert into positions(name, guarentee, serial_numb, seria, color, country, number, price) values ('Samsung', 3, 22222, 'Moon', 'White', 'Korea', 575757, 78000);
insert into positions(name, guarentee, serial_numb, seria, color, country, number, price) values ('Vetek', 1, 333333, 'Home', 'White', 'Freez', 7878787, 38550);

insert into products(name, positions_id) values ('refrigerator', 1);
insert into products(name, positions_id) values ('refrigerator', 2);
insert into products(name, positions_id) values ('refrigerator', 3);
insert into products(name) values ('Philips');
insert into products(name) values ('Big');