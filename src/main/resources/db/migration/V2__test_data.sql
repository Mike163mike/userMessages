INSERT INTO users (create_time, edit_time, email, username, password, enable, first_name, middle_name, last_name)
VALUES (now(),
        now(),
        'some_data@google.com',
        'BenderTheBest',
        '$2y$12$ysBy/aXV8Ynbzme.cGURLOJoEv9JtOvHBX4bfC3dfK5iVO84Yyf82',
        true,
        'Bender',
        'FatherName',
        'Rodriguez'),
       (now(),
        now(),
        'second_data@gmail.com',
        'Mike',
        '$2y$12$ysBy/aXV8Ynbzme.cGURLOJoEv9JtOvHBX4bfC3dfK5iVO84Yyf82',
        true,
        'Mickael',
        'FatherWas',
        'Tyson');
INSERT INTO role (name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER');

INSERT INTO users_roles (user_id, roles_id)
VALUES (1, 1),
       (2, 2);

INSERT INTO message (create_time, edit_time, message, user_id)
VALUES (now(),
        now(),
        'Test message #1 from Bender.',
        1),
       (now(),
        now(),
        'Test message #2 from Tyson.',
        2);