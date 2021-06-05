--select s.name, ss.name from product as s join type as ss on s.type_id = ss.id
--where s.type_id in (1) 

--select * from product where name like 'Мороженое%'
--select * from product where name like '_ороженое%';

--select s.name, s.expired_date from product as s
--where (current_date) > s.expired_date;

--select ss.name, max(s.price) from product as s inner join type as ss on s.type_id = ss.id
--group by ss.name;
--select max(price) from product;

--select ss.name, count(s.name) from product as s inner join type as ss on s.type_id = ss.id
--group by ss.name

--select s.name, ss.name from product as s inner join type as ss 
--on s.type_id = ss.id
--where ss.name = 'Сыр' or ss.name = 'Молоко';

--select s.name, s.unit from product as s
--where s.unit < 10;

--select s.name, ss.name from product as s inner join type as ss on s.type_id = ss.id;