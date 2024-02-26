drop table if exists employee cascade ;
drop sequence if exists employee_seq;
create sequence employee_seq start with 1 increment by 50;
create table employee (id integer not null  AUTO_INCREMENT , is_manager boolean, salary integer, date_of_joining timestamp(6) with time zone, name varchar(255), primary key (id));
INSERT INTO employee (name,is_manager,salary,date_of_joining) VALUES ('Ravindra Ahirekar', true,10000,'2010-01-01 10:00:00+01');
INSERT INTO employee (name,is_manager,salary,date_of_joining) VALUES ('Aviraj Ahirekar', false,20000,'2010-04-01 10:00:00+01');
INSERT INTO employee (name,is_manager,salary,date_of_joining) VALUES ('Priya Ahirekar', false,30000,'2013-05-01 10:00:00+01');
INSERT INTO employee (name,is_manager,salary,date_of_joining) VALUES ('Shankar Ahirekar', false,40000,'2017-08-01 10:00:00+01');