insert into users ( id,
                    created_date,
                    status,
                    update_date,
                    email,
                    first_name,
                    last_name,
                    password,
                    username)
values (1, '1999-12-09', 'ACTIVE', '2020-12-09','babnikgo@gmail.com','Andriy','Hishchak','$2y$12$5THRrwoTQOIreQvlxj8ti.wVVgllGHrolWEZbqfRqCZTzQpouZtUG','admin'),
       (2, '1999-12-09', 'ACTIVE', '2020-12-09','oleg@gmail.com','Oleg','Bereshanskiy','$2a$04$BwckLM2txur4ZAX3MimgmuXudViN6MbLQAxls3V.5E30N9NR5Qr0K','user');

insert into roles ( id,
                    created_date,
                    status,
                    update_date,
                    name_role)
values (1, '1999-12-09', 'ACTIVE', '2020-12-09','ROLE_ADMIN'),
       (2, '1999-12-09', 'ACTIVE', '2020-12-09','ROLE_USER');

insert into user_roles (user_id,role_id)
values (1,1),(1,2),(2,2);