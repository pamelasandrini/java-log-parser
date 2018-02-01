-- USE H2SQL logparser database

CREATE TABLE REQUESTS(ID INT PRIMARY KEY auto_increment, IP VARCHAR(255), REQUEST_DATE TIMESTAMP, REQUEST VARCHAR(255), STATUS VARCHAR(255), USER_AGENT VARCHAR(255));


CREATE TABLE BLOCKED_IPS(ID INT PRIMARY KEY auto_increment, IP VARCHAR(255), BLOCK_REASON VARCHAR(255));