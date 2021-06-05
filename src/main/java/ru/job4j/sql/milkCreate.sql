create table type(
 id serial primary key,
 name varchar(250)
);
create table product(
 id serial primary  key,
 name varchar(250),
 type_id int references type(id),
 expired_date DATE NOT NULL,
 price int,
 unit int 
);

insert into type(name) values('Сыр');
insert into type(name) values('Молоко');
insert into type(name) values('Мороженое');

insert into product(name, type_id, expired_date, price, unit) values('Сыр Пошехонский', 1, '2021-06-04', 450, 9);
insert into product(name, type_id, expired_date, price, unit) values('Сыр Волжский', 1, '2021-05-27', 379, 30);
insert into product(name, type_id, expired_date, price, unit) values('Сыр Мааздан', 1, '2021-05-23', 657, 55);
insert into product(name, type_id, expired_date, price, unit) values('Сыр Косичка', 1, '2021-05-28', 330, 110);
insert into product(name, type_id, expired_date, price, unit) values('Молоко Обезжиренное', 2, '2021-06-07', 55, 150);
insert into product(name, type_id, expired_date, price, unit) values('Молоко Фруктовое', 2, '2021-06-05', 60, 35);
insert into product(name, type_id, expired_date, price, unit) values('Молоко Соевое', 2, '2021-06-25', 110, 110);
insert into product(name, type_id, expired_date, price, unit) values('Молоко Шоколадное', 2, '2021-06-17', 78, 61);
insert into product(name, type_id, expired_date, price, unit) values('Мороженое 48 копеек', 3, '2021-06-15', 98, 35);
insert into product(name, type_id, expired_date, price, unit) values('Мороженое в стаканчике', 3, '2021-06-15', 35, 8);
insert into product(name, type_id, expired_date, price, unit) values('Мороженое с фруктами', 3, '2021-06-03', 47, 25);