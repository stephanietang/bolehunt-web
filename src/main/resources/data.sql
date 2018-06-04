-- user
INSERT INTO user(username, password, name, enabled) VALUES ('username1', '$2a$16$hwhqy1Gz6KFSMZbVUv/N/eKAX44OlzP1kz.Vt8Kco8H2MS/HSt0WW', 'name1', 1); -- password : userpwd1
INSERT INTO user(username, password, name, enabled) VALUES ('username2', '$2a$16$VVu1PzAJ0bND3EtwxjIwAOopyxUAHWKpwCPUvETT9MI7mO1yp7sXi', 'name2', 1); -- password : userpwd2

-- role
INSERT INTO role(role_name) values ('ROLE_USER');
INSERT INTO role(role_name) values ('ROLE_ADMIN');

-- user_role
INSERT INTO user_role(user_id, role_id) VALUES (1, 1);
INSERT INTO user_role(user_id, role_id) VALUES (1, 2);
INSERT INTO user_role(user_id, role_id) VALUES (2, 1);