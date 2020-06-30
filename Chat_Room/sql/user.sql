delimiter $

drop database if exists chatroom;
create database chatroom;# DEFAULT CHARACTER SET gbk COLLATE gbk_chinese_ci;
use chatroom;

create table user(
username varchar(20) not null,
passwd varchar(20) not null,
grade varchar(2),
birth varchar(3),
sex char(4),
email varchar(20),# not null
phone varchar(11),
registertime datetime default null,
primary key(username) 
);#ENGINE=InnoDB DEFAULT CHARSET=gbk;
$
