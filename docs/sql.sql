INSERT INTO quiz.role(id, name, created_user, active)
VALUES ( 1, 'ADMIN', 'ADMIN',true);
	
INSERT INTO quiz.user(id, username, password,  created_user,active)
VALUES (1, 'admin', '21232F297A57A5A743894A0E4A801FC3', 'admin',1);


INSERT INTO quiz.user_role( role_id, user_id)
VALUES (1, 1);