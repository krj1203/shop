--��ǰ
	--��ǰID : ��ID+code
	--��ǰ��
	--������/���Կ�
	--�ǸŰ���
	--�ϹݼҺ��ڰ���
	--ī���Һο���
	--�˻���
	--��ǰ����
	--��ǰ�������
	--��ǰ�������
	
DROP TABLE Product ;
CREATE TABLE Product (
       ProductID              VARCHAR(7) NOT NULL,
       MallID		     VARCHAR(4) NOT NULL,
       ProductName           VARCHAR(40) NOT NULL,
       Company              VARCHAR(40) NOT NULL,
       Price1               int NOT NULL,
       Price2               int ,
       Install              CHAR(1) NOT NULL,
       keyword              VARCHAR(50) NOT NULL,
       Detail               VARCHAR(100) ,
       ProductDate         DATE NOT NULL,
       PHOTODIR	VARCHAR(100)	 
);

ALTER TABLE Product
       ADD   PRIMARY KEY (ProductID, MallID)  ;

--��ٱ���
	--�ֹ���ȣ
	--��ǰID
	--ȸ��ID
	--����
	--����
DROP TABLE Basket ;
CREATE TABLE Basket (
       OrderNum             int NOT NULL,
       ProductID            VARCHAR(7) NOT NULL,
       MemID		    VARCHAR(15) NOT NULL,	
       Quantity             int NOT NULL,
       Price                int NOT NULL
);

ALTER TABLE Basket
       ADD   PRIMARY KEY (OrderNum)  ;

--������
	--�ֹ���ȣ
	--�ּұ���(1:��,2:����)
	--ȸ��ID
	--�������̸�
	--�������ּ�
	--����ó
	--�̸���
	--������(CARD / REMIT)
	--���Ż�ǰ����
	--�ѱ��ž�
	--ó������(Y/N)
	--ī������
	--ī���ȣ
DROP TABLE Purchaser ;
CREATE TABLE Purchaser (
       OrderNum             int NOT NULL,
       Place		    CHAR(1) NOT NULL,
       MemID                VARCHAR(15) NOT NULL,
       Name                 VARCHAR(20) NOT NULL,
       Address              VARCHAR(70) NOT NULL,
       Tel		    VARCHAR(20) ,
       Email		    VARCHAR(60) ,
       PayType              CHAR(5) NOT NULL,
       Tcount               int ,
       Amount               int NOT NULL,
       PayStatus            CHAR(1) ,
       PurchaseDate         DATE NOT NULL,
       CardType		    VARCHAR(10) ,		
       CardNumber	    VARCHAR(16) 
);

ALTER TABLE Purchaser
       ADD   PRIMARY KEY (OrderNum)  ;


commit;