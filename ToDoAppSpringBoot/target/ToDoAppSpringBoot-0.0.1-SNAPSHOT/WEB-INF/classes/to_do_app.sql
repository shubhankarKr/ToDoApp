
show databases;
CREATE DATABASE  IF NOT EXISTS `to_do_db`;
USE `to_do_db`;
-- drop database to_do_db;

-- DROP TABLE IF EXISTS `task_user`;

CREATE TABLE `to_do_list` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT,
  `created_Date` date DEFAULT NULL,
  `last_Updated_Date` varchar(20) NOT NULL,
  `desciption` varchar(250) NOT NULL,
  `title` varchar(100) NOT NULL,
  `color_id` int (11),
  `user_name` int not null,
  PRIMARY KEY (`task_id`),
  constraint fk_MD_color foreign key(color_id) references md_color_code(color_id),
  constraint fk_user_name foreign key(user_name) references task_user(user_name)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `md_color_code`;

create TABLE md_color_code(
color_id int(10) NOT NULL AUTO_INCREMENT,
color_code varchar(200) NOT NULL DEFAULT '#FF7F50',
primary KEY ( `color_id`)
);

-- ALTER TABLE to_do_list ADD FOREIGN KEY(color_id) REFERENCES md_color_code(color_id);
-- update to_do_list set color_id=1 where id<>1;
 INSERT INTO md_color_code  VALUES (NULL,'#C9A0DC');
 INSERT INTO md_color_code  VALUES (NULL,'#FF7F50');
-- INSERT INTO md_color_code  VALUES (3,'#b0e0e6');
-- INSERT INTO md_color_code  VALUES (4,'#93ccea');

select * from md_color_code;
select * from to_do_list;


CREATE TABLE `task_user` (
`user_id` INT NOT NULL AUTO_INCREMENT,
`user_name` VARCHAR(45) UNIQUE NOT NULL,
`password` VARCHAR(200) NOT NULL,
`role` varchar(50) NOT NULL default 'USER',
PRIMARY KEY (`user_id`));

create table authorities (
	id int not null auto_increment primary key,
	user_name varchar(50) UNIQUE not null,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(id) references task_user(user_id)
);
-- create unique index ix_auth_username on authorities (username,authority);

insert into to_do_list (task_id,title, desciption,user_name ) values (1,'Testing Title', 'Testing Description','Clerk');

select * from md_color_code;
select * from to_do_list order by task_id desc;
select * from task_user;


