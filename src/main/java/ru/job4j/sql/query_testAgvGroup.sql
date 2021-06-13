select ss.name, avg(z.price) from devices_people as s join people as ss on s.people_id = ss.id join devices as z on s.device_id = z.id 
group by ss.name;