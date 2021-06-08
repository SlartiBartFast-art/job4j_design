create table departments(
 id serial primary key,
 name varchar(255)
);

create table employees(
 id serial primary key,
 name varchar(255),
 depart_id int references departments(id)
);

insert into departments (name) values('Транспортный отдел');
insert into departments (name) values('Отдел развития');
insert into departments (name) values('Финансовый отдел');
insert into departments (name) values('Юридический отдел');
insert into departments (name) values('Отдел кадров');
insert into departments (name) values('Информационный отдел');
insert into departments (name) values('Отдел маркетинга');

insert into employees(name, depart_id) values('Иванов И.И.', 1);
insert into employees(name, depart_id) values('Смирнов А.П.', 2);
insert into employees(name, depart_id) values('Васильев И.А.', 3);
insert into employees(name, depart_id) values('Михайлов Р.Б', 4);
insert into employees(name, depart_id) values('Морозов П.Д', 5);

create table teens(
 id serial primary key,
 name varchar(255),
 gender varchar(255)
);

insert into teens(name, gender) values('Иванов И.И', 'man');
insert into teens(name, gender) values('Васильева И.А.', 'female');
insert into teens(name, gender) values('Морозов П.Д.', 'man');
insert into teens(name, gender) values('Лебедева Е.Б.', 'female');
insert into teens(name, gender) values('Семенов М.А.', 'man');
insert into teens(name, gender) values('Павлова Т.С.', 'female');