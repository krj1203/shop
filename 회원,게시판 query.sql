
-- ��ȣ ������ ����


--1. ȸ�� ���̺� ------------------------------------------------

create table jsp_member
(
  id 		varchar2(50) 	not null primary KEY
  ,password 	varchar2(50) 	not null
  ,name 		varchar2(50)
  ,gender 		varchar2(10)
  ,birth 		date
  ,mail 		varchar2(100)
  ,phone 		varchar2(50)
  ,address 		varchar2(200)
  ,reg date 	default sysdate
);

-----------------------------------------------------------------
select * from jsp_member;
--2. �Խ��� ���̺� ----------------------------------------------

select * from Member_Board;
drop table Member_Board; 
CREATE TABLE Member_Board 
(
  board_num 		NUMBER 		NOT NULL,
  board_id 		VARCHAR2(50),
  board_subject 		VARCHAR2(100),
  board_content 		VARCHAR2(2000),
  board_file 		VARCHAR2(100),
  Board_re_ref 		NUMBER,
  Board_re_lev 		NUMBER,
  Board_re_seq 		NUMBER,
  Board_count 		NUMBER,
  Board_date		DATE,
  BOARD_PARENT     	NUMBER,              
  CONSTRAINT PK_Member_Board PRIMARY KEY(board_num) --�������� 
);


-- 3. �Խ��� ���̺� �������� �߰�
 
alter table MEMBER_BOARD
add constraint pk_board_id foreign key(board_id)--JSP_MEMBER(id)�� �����ؾ���--
REFERENCES JSP_MEMBER(id); 

-----------------------------------------------------------------


-- 4. �湮�� �� ���̺� -------------------------------------------

CREATE TABLE VISIT (V_DATE DATE NOT NULL);

-----------------------------------------------------------------

-- 5. ��� ���̺� ------------------------------------------------

CREATE TABLE BOARD_COMMENT 
(
  COMMENT_NUM 		NUMBER 		NOT NULL,
  COMMENT_BOARD 	NUMBER 		NOT NULL,
  COMMENT_ID 		VARCHAR2(15),
  COMMENT_DATE 		DATE,
  COMMENT_PARENT 	NUMBER,
  COMMENT_CONTENT 	VARCHAR2(1000) 	NOT NULL,
  CONSTRAINT PK_comment PRIMARY KEY(COMMENT_NUM),
  CONSTRAINT FK_comment FOREIGN KEY(COMMENT_BOARD) REFERENCES MEMBER_BOARD(BOARD_NUM) --�ܷ�Ű
);
 
-- 6. ��� ���̺� ���� �������� ����

ALTER TABLE BOARD_COMMENT DROP CONSTRAINT "COMMENT_BOARD"
 
-- 7. �������� ON DELETE CASCADE �߰�

ALTER TABLE BOARD_COMMENT ADD CONSTRAINT COMMENT_BOARD
         FOREIGN KEY(COMMENT_BOARD) REFERENCES MEMBER_BOARD(BOARD_NUM) ON DELETE CASCADE ;

-----------------------------------------------------------------

-- 8. �Խ��� ������

create sequence BOARD_NUM; 

-- 9. ��� ������ 

create sequence COMMENT_SEQ; 


select FROM (SELECT ProductID, MallID, ProductName, CompanyPrice1, Price2,Install,keyword, Detail, productDate, PHOTODIR FROM Product)


