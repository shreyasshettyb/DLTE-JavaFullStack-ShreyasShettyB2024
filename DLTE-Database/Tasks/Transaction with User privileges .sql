create user varun identified by hello;

create user prasanth identified by hello1;


create user rakesh identified by hello2;


create user akash identified by hello3;

create user elroy identified by hello4;

create table userlist(name varchar2(20),address varchar2(20),phone number(10));

 grant select on userlist to varun;

 grant delete on userlist to prasanth;

 grant select on userlist to rakesh;

 grant insert on userlist to akash;

grant update on userlist to elroy;