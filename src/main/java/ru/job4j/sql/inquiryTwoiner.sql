select pp.name, p.name, p.guarentee, p.serial_numb from products as pp join positions as p on pp.positions_id = p.id;