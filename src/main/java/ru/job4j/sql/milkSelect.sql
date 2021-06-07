--select ss.name, s.name from product as s join type as ss on s.type_id = ss.id
--where ss.name = 'Сыр';

--select * from product where name like 'Мороженое%'
--select * from product where name like '_ороженое%';

--select s.name, s.expired_date from product as s
--where (current_date) > s.expired_date;

--select * from product where price = (select MAX(price) from product);

--select ss.name, count(s.name) from product as s inner join type as ss on s.type_id = ss.id
--group by ss.name

--select s.name, ss.name from product as s inner join type as ss 
--on s.type_id = ss.id
--where ss.name = 'Сыр' or ss.name = 'Молоко';

--select ss.name from product as s inner join type as ss on s.type_id = ss.id
--group by ss.name
--having count (s.name) < 10;

--select s.name, ss.name from product as s inner join type as ss on s.type_id = ss.id;