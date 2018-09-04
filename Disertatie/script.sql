CREATE TABLE DIC_TASK_COMMENT(
    ID NUMBER(10), 
    TASK_ID NUMBER(10), 
    USER_ID NUMBER(10),
    TASK_COMMENT VARCHAR2(300), 
    CREATED DATE, 
    PRIMARY KEY(ID),
    CONSTRAINT FK_COMM_TASK FOREIGN KEY(TASK_ID) REFERENCES DIC_TASK(ID),
    CONSTRAINT FK_COMM_USER FOREIGN KEY (USER_ID) REFERENCES DIC_PERSO(USER_ID)
    );
CREATE TABLE DIC_TASK_ITERATION(
    ID NUMBER(10), 
    START_DATE DATE,
    END_DATE DATE,
    PRIMARY KEY(ID)
    );
ALTER TABLE DIC_TASK ADD TASK_ITERATION NUMBER(10);

ALTER TABLE DIC_TASK ADD CONSTRAINT FK_TASK_ITERATION FOREIGN KEY(TASK_ITERATION) REFERENCES DIC_TASK_ITERATION(ID);

CREATE TABLE REF_LEVEL(LEVEL_ID NUMBER(2), TOTAL_POINTS NUMBER(6), POINTS_FOR_NEW_LEVEL NUMBER(6), PRIMARY KEY(LEVEL_ID));

CREATE TABLE DIC_USER_LEVEL(USER_ID NUMBER(19), LEVEL_ID NUMBER(2), TOTAL_POINTS NUMBER(6), PERCENTAGE_LEVEL NUMBER(3), CREATED DATE, MODIFIED DATE,
    PRIMARY KEY(USER_ID), CONSTRAINT FK_USER_LEVEL FOREIGN KEY (USER_ID ) REFERENCES DIC_PERSO(USER_ID),
    CONSTRAINT FK_LEVEL_LEVEL FOREIGN KEY(LEVEL_ID) REFERENCES REF_LEVEL(LEVEL_ID));

ALTER TABLE REF_LEVEL ADD WEIGHT NUMBER(3,2);

Insert into REF_LEVEL (LEVEL_ID,TOTAL_POINTS,POINTS_FOR_NEW_LEVEL,WEIGHT) values (10,40000,null,0.8);
Insert into REF_LEVEL (LEVEL_ID,TOTAL_POINTS,POINTS_FOR_NEW_LEVEL,WEIGHT) values (9,30000,10000,0.8);
Insert into REF_LEVEL (LEVEL_ID,TOTAL_POINTS,POINTS_FOR_NEW_LEVEL,WEIGHT) values (8,23000,7000,0.8);
Insert into REF_LEVEL (LEVEL_ID,TOTAL_POINTS,POINTS_FOR_NEW_LEVEL,WEIGHT) values (7,17500,5500,0.9);
Insert into REF_LEVEL (LEVEL_ID,TOTAL_POINTS,POINTS_FOR_NEW_LEVEL,WEIGHT) values (6,13000,4500,0.9);
Insert into REF_LEVEL (LEVEL_ID,TOTAL_POINTS,POINTS_FOR_NEW_LEVEL,WEIGHT) values (5,9500,3500,1);
Insert into REF_LEVEL (LEVEL_ID,TOTAL_POINTS,POINTS_FOR_NEW_LEVEL,WEIGHT) values (4,6500,3000,1);
Insert into REF_LEVEL (LEVEL_ID,TOTAL_POINTS,POINTS_FOR_NEW_LEVEL,WEIGHT) values (3,4000,2500,1.1);
Insert into REF_LEVEL (LEVEL_ID,TOTAL_POINTS,POINTS_FOR_NEW_LEVEL,WEIGHT) values (2,2000,2000,1.2);
Insert into REF_LEVEL (LEVEL_ID,TOTAL_POINTS,POINTS_FOR_NEW_LEVEL,WEIGHT) values (1,1000,1000,1.2);
Insert into REF_LEVEL (LEVEL_ID,TOTAL_POINTS,POINTS_FOR_NEW_LEVEL,WEIGHT) values (0,0,1000,1.2);

INSERT INTO REF_TASK_END (ID, NAME, WEIGHT) VALUES ('1', 'terminat <= 3 zile inainte', '1.2');
INSERT INTO REF_TASK_END (ID, NAME, WEIGHT) VALUES ('2', 'terminat 1 zile inainte', '1.05');
INSERT INTO REF_TASK_END (ID, NAME, WEIGHT) VALUES ('3', 'terminat la fix', '1');
INSERT INTO REF_TASK_END (ID, NAME, WEIGHT) VALUES ('4', 'intariere 1 zi', '0.9');

CREATE SEQUENCE  id_task_comm_seq  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE;
CREATE SEQUENCE  id_task_iter_seq  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
