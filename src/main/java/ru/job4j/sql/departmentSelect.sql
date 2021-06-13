/*select * from employees as s left join departments as ss on s.depart_id = ss.id;
select * from departments as ss right join employees as s on s.depart_id = ss.id;
select * from employees as s full join departments as ss on s.depart_id = ss.id;
select * from employees as s cross join departments as ss;*/

/*select * from departments as s left join employees as ss on s.id = ss.depart_id;*/

/*select * from employees as s left join departments as ss on s.depart_id = ss.id;
select * from departments as ss right join employees as s on s.depart_id = ss.id;
select * from departments as s left join employees as ss on s.id = ss.depart_id;
select * from employees as s right join departments as ss on ss.id = s.depart_id;*/

/*select n1.name as a, n2.name as b, concat(n1.gender, ' ', n2.gender) as "marriage" from teens n1 
cross join teens as n2
where concat(n1.gender, ' ', n2.gender) = 'man female' or concat(n1.gender, ' ', n2.gender) = 'female man';*/
