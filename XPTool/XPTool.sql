CREATE TABLE SCRUM_DATA
(
 SCRUM_DATA_ID NUMBER(18)CONSTRAINT PK_SCRUM_DATA PRIMARY KEY,
 SCRUM_DATE DATE,
 USER_ID NUMBER(18),
 TASK VARCHAR2(4000),
 HOURS_SPENT NUMBER(10,2)
);

CREATE TABLE USERS
(
 USER_ID NUMBER(18)CONSTRAINT PK_USER PRIMARY KEY,
 USERNAME VARCHAR2(50),
 PASSWORD VARCHAR2(50),
 EMAIL VARCHAR2(50)
)
;

ALTER TABLE XPTOOL.SCRUM_DATA ADD 
CONSTRAINT SCRUM_DATA_R01
 FOREIGN KEY (USER_ID)
 REFERENCES XPTOOL.USERS (USER_ID) ENABLE
 VALIDATE;
 
create sequence SCRUM_DATA_seq
start with 1
increment by 1;


create sequence USERS_seq
start with 1
increment by 1;