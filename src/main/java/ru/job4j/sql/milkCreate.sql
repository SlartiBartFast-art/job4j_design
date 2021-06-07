create table type(
 id serial primary key,
 name varchar(250)
);
create table product(
 id serial primary  key,
 name varchar(250),
 type_id int references type(id),
 expired_date DATE NOT NULL,
 price int 
);

insert into type(name) values('Сыр');
insert into type(name) values('Молоко');
insert into type(name) values('Мороженое');

insert into product(name, type_id, expired_date, price) values('Сыр Пошехонский', 1, '2021-06-04', 450);
insert into product(name, type_id, expired_date, price) values('Сыр Волжский', 1, '2021-05-27', 379);
insert into product(name, type_id, expired_date, price) values('Сыр Мааздан', 1, '2021-05-23', 657);
insert into product(name, type_id, expired_date, price) values('Сыр Вешенка', 1, '2021-05-28', 330);
insert into product(name, type_id, expired_date, price) values('Сыр с ветчиной', 1, '2021-06-28', 330);
insert into product(name, type_id, expired_date, price) values('Сыр копченый на углях', 1, '2021-05-25', 330);
insert into product(name, type_id, expired_date, price) values('Сыр Адыгейский', 1, '2021-06-18', 430);
insert into product(name, type_id, expired_date, price) values('Сыр Костромской', 1, '2021-06-08', 330);
insert into product(name, type_id, expired_date, price) values('Сыр Плавленный', 1, '2021-05-29', 290);
insert into product(name, type_id, expired_date, price) values('Сыр Колбасный', 1, '2021-06-09', 310);
insert into product(name, type_id, expired_date, price) values('Сыр Обезжиренный', 1, '2021-06-19', 450);
insert into product(name, type_id, expired_date, price) values('Молоко Обезжиренное', 2, '2021-06-07', 55);
insert into product(name, type_id, expired_date, price) values('Молоко Фруктовое', 2, '2021-06-05', 60);
insert into product(name, type_id, expired_date, price) values('Молоко Соевое', 2, '2021-06-25', 110);
insert into product(name, type_id, expired_date, price) values('Молоко Шоколадное', 2, '2021-06-17', 78);
insert into product(name, type_id, expired_date, price) values('Мороженое 48 копеек', 3, '2021-06-15', 98);
insert into product(name, type_id, expired_date, price) values('Мороженое в стаканчике', 3, '2021-06-15', 35);
insert into product(name, type_id, expired_date, price) values('Мороженое с фруктами', 3, '2021-06-03', 47);