--1) Вывести список всех машин и все привязанные к ним детали.
/*select t1.name, t2.name, t3.name, t4.name
from car as t1
join carcase as t2 on t2.id = t1.carcase_id
join engine as t3 on t3.id = t1.engine_id
join transmission as t4 on t4.id = t1.transmission_id
group by t1.name, t2.name, t3.name, t4.name;*/

/*Вывести отдельно детали (1 деталь - 1 запрос), 
которые не используются НИ в одной машине, кузова, двигатели, коробки передач.*/
/*select s.name, ss.carcase_id from carcase as s left join car as ss on s.id = ss.carcase_id
where ss.carcase_id is null;*/

/*select s.name, ss.engine_id from engine as s left join car as ss on s.id = ss.engine_id
where engine_id is null;*/

select s.name, ss.transmission_id from transmission as s left join car as ss on s.id = ss.transmission_id 
where ss.transmission_id is null;
