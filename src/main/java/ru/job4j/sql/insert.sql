insert into state(name) values('state');
insert into rules(name) values('rules');
insert into role(name) values('role');
insert into users(name, role_id) values('users', 1);
insert into category(name) values('category');
insert into role_rules(name, role_id, rules_id) values('role_rules', 1, 1);
insert into item(name, state_id, category_id, users_id) values('item', 1, 1, 1);
insert into comments(name, item_id) values('comments', 1);
insert into attaches(name, item_id) values('attaches', 1); 