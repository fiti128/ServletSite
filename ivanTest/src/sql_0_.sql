#create shema site;
#use site;


SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE USER;
DROP TABLE ROLE;




/* Create Tables */

CREATE TABLE ROLE
(
	ROLE_ID INT UNSIGNED NOT NULL AUTO_INCREMENT,
	NAME VARCHAR(32) NOT NULL UNIQUE,
	PRIMARY KEY (ROLE_ID)
);


CREATE TABLE USER
(
	USER_ID INT UNSIGNED NOT NULL AUTO_INCREMENT,
	NAME VARCHAR(32) NOT NULL,
	PASSWORD VARCHAR(32) NOT NULL,
	ROLE_ID INT UNSIGNED NOT NULL,
	PRIMARY KEY (USER_ID)
) ENGINE = InnoDB;



/* Create Foreign Keys */

ALTER TABLE USER
	ADD FOREIGN KEY (ROLE_ID)
	REFERENCES ROLE (ROLE_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;
insert into `role` (`name`)
values('user');
insert into `role` (`name`)
values('admin');

insert into `user` (`name`,`password`, `role_id`)
values('admin','password',(select role_id from role where name='admin'));

insert into `user` (`name`,`password`, `role_id`)
values ('user','password',(select role_id from role where name='user'));
