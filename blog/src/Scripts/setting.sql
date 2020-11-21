
/*테이블 스페이스 생성*/
CREATE TABLESPACE NUSANGTABLE DATAFILE 'C:\oraclexe\app\oracle\oradata\springtablespace.dbf' SIZE  250M ;

/*유저 생성*/
CREATE USER spbooter IDENTIFIED BY 4521 DEFAULT TABLESPACE NUSANGTABLE TEMPORARY TABLESPACE TEMP;

/*권한 주기*/
GRANT connect, resource, dba TO spbooter;

