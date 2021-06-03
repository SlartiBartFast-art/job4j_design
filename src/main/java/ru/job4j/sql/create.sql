create table category(
  id serial primary key,
  name varchar(50)
);
create table state(
  id serial primary key,
  name varchar(50)
);
create table rules(
  id serial primary key,
  name varchar(50)
);
create table role(
  id serial primary key,
  name varchar(50)  
);
create table role_rules(
  id serial primary key,
  name varchar(50),
  role_id int references role(id),
  rules_id int references rules(id)
);
create table users(
  id serial primary key,
  name varchar(50),
  role_id int references role(id) 
);
create table item(
  id serial primary key,
  name varchar(50),
  state_id int references state(id),
  category_id int references category(id),
  users_id int references users(id)  
);
create table comments(
  id serial primary key,
  name varchar(50),
 item_id int references item(id)
);
create table attaches(
  id serial primary key,
  name varchar(50),
  item_id int references item(id)
);
