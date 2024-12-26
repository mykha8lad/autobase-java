insert into Driver(id, name, surname, num_tel, experience, total_sum, is_free, encrypted_password)
values (1, 'admin', 'Super admin', '+380654785412', -1, 0, false, '$2a$10$NlXcRl7SyECPSmc3hEThl.AByQe6fLRk5XUJO6Rd5x.ghAmXPkVwi');

insert into role(id, role_name)
values (1, 'ROLE_ADMIN'),
       (2, 'ROLE_USER');

insert into user_role(id, user_id, role_id)
values (1, 1, 1);
