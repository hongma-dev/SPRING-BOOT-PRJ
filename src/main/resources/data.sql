
create table User (id integer not null, join_date timestamp, name varchar(255), password varchar(255), ssn varchar(255), primary key (id));

insert into User (id,join_date,name,password,ssn) values (1, sysdate(), 'hongma','test1','701010-1111111');
insert into User (id,join_date,name,password,ssn) values (2, sysdate(), 'hogdev','test2','701010-1211111');
insert into User (id,join_date,name,password,ssn) values (3, sysdate(), 'hongmasan','test3','701011-1111111');

create table Post (id integer not null, description varchar(255), user_id integer not null , primary key (id));
insert into Post (id,description, user_id) values (10001,  'hongmasan' ,1);
insert into Post (id,description, user_id) values (10002,  'hongma' ,1);
insert into Post (id,description, user_id) values (10003,  'hongmadev' ,1);