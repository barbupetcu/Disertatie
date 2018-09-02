CREATE TABLE DIC_TASK (ID NUMBER(10), USER_ID NUMBER(10), TITLE VARCHAR2(100), DEADLINE DATE, PRIORITY NUMBER(2), DESCRIPTION VARCHAR2(300), difficulty NUMBER(2), 
    PRIMARY KEY(ID), 
    CONSTRAINT fk_user 
        FOREIGN KEY(USER_ID)
        REFERENCES DIC_PERSO(USER_ID),
    CONSTRAINT fk_PRIORITY
        FOREIGN key (priority)
        references ref_priority(id),
    CONSTRAINT FK_DIFFICULTY
        FOREIGN KEY(DIFFICULTY)
        REFERENCES REF_DIFFICULTY(ID));
        
CREATE TABLE REF_PRIORITY (ID NUMBER(2), NAME VARCHAR2(32), PRIMARY KEY (ID));

CREATE TABLE REF_DIFFICULTY (ID NUMBER(2), NAME VARCHAR2(32), QUOTE NUMBER(3), PRIMARY KEY (ID));

alter table dic_perso add constraint fk_perso_user 
    FOREIGN KEY(user_id) references app_user(id);
    

alter table dic_perso add constraint fk_perso_dept 
    FOREIGN KEY(DEPT) references dept(id);


alter table dept_manager add constraint fk_dept_dept 
    FOREIGN KEY(dept_id) references dept(id);

delete from dept_manager where manager_id not in (select distinct user_id from dic_perso);
alter table dept_manager add constraint fk_dept_manager 
    FOREIGN KEY(manager_id) references dic_perso(user_id);

alter table dic_task add created date;
alter table dic_task add modified date;


Insert into REF_PRIORITY (ID,NAME) values (1,'Low');
Insert into REF_PRIORITY (ID,NAME) values (2,'Medium');
Insert into REF_PRIORITY (ID,NAME) values (3,'High');

Insert into REF_DIFFICULTY (ID,NAME,QUOTE) values (1,'Easy',50);
Insert into REF_DIFFICULTY (ID,NAME,QUOTE) values (2,'Medium',80);
Insert into REF_DIFFICULTY (ID,NAME,QUOTE) values (3,'Diffcult',100);

CREATE TABLE REF_TASK_STATUS (ID NUMBER(2), NAME VARCHAR2(32), PRIMARY KEY(ID));

Insert into REF_TASK_STATUS (ID,NAME) values (1,'Backlog');
Insert into REF_TASK_STATUS (ID,NAME) values (2,'Waiting');
Insert into REF_TASK_STATUS (ID,NAME) values (3,'In progress');
Insert into REF_TASK_STATUS (ID,NAME) values (4,'Done');

alter table dic_task add status number(2);

alter table dic_task add constraint fk_task_status
    foreign key(status)
    REFERENCES ref_task_status(id);

CREATE SEQUENCE id_task_seq
  MINVALUE 1
  MAXVALUE 9999999999
  START WITH 1
  INCREMENT BY 1;

 
create table ref_task_end (id number(2), name varchar2(32), weight number(3,2), primary key(id));

alter table dic_task add delay number(2);

alter table dic_task add end_date date;

alter table dic_task add constraint fk_task_end
    foreign key(delay)
    REFERENCES ref_task_end(id);


