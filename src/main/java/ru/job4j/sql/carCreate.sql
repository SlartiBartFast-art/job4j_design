create table carcase(
 id serial primary key,
 name varchar(255)
);

create table engine(
 id serial primary key,
 name varchar(255)
);

create table transmission(
 id serial primary key,
 name varchar(255)
);

create table car(
 id serial primary key,
 name varchar(255),
 carcase_id int references carcase(id),
 engine_id int references engine(id),
 transmission_id int references transmission(id)
);

insert into carcase(name) values('wagon');
insert into carcase(name) values('sedan');
insert into carcase(name) values('hatchback');
insert into carcase(name) values('cabrio');
insert into carcase(name) values('track');

insert into engine(name) values('v4 1.6 125 л.с');
insert into engine(name) values('v5 2.5 175 л.с.');
insert into engine(name) values('v6 3.0 220 л.с.');
insert into engine(name) values('v8 3.5 320 л.с.');

insert into transmission(name) values('manual');
insert into transmission(name) values('automat');
insert into transmission(name) values('robot');
insert into transmission(name) values('variator');

insert into car(name, carcase_id, engine_id, transmission_id) values('Ford Mustang', 4, 3, 2);
insert into car(name, carcase_id, engine_id, transmission_id) values('Dodge Ram', 5, 4, 2);
insert into car(name, carcase_id, engine_id, transmission_id) values('Ford Focus', 1, 1, 1);
insert into car(name, carcase_id, engine_id, transmission_id) values('Opel Astra', 2, 1, 3);
insert into car(name, carcase_id, engine_id, transmission_id) values('Renoult Fluence', 2, 1, 2);