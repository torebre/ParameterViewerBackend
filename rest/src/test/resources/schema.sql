create table Test (
idTime INTEGER PRIMARY KEY,
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