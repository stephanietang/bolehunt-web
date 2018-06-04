drop table if exists user;
drop table if exists role;
drop table if exists user_role;

create table user (
	id int not null auto_increment, 
	username varchar(255) not null, 
	password varchar(255) not null,
	name varchar (255) not null,
	enabled int not null,
	primary key (id)
);

create table role (
  id int not null auto_increment,
  role_name varchar(16) not null,
  primary key (id)
);
	
create table user_role (
	id int not null auto_increment, 
	user_id int not null,
	role_id int not null,
	primary key (id)
);