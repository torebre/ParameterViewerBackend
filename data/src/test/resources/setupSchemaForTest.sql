--create table Test (
--idTime DATETIME PRIMARY KEY,
---- id VARCHAR(256) PRIMARY KEY,
--value1 DOUBLE
--);


create table Test (
idTime INTEGER PRIMARY KEY,
--logId INTEGER,
value1 DOUBLE
);

create table Parameters (
name VARCHAR(50)
);

create Table Events (
id INT PRIMARY KEY,
logId INT,
start INT,
stop INT
);