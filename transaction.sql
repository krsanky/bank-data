CREATE TABLE transaction (
id SERIAL,
dt date,
account varchar, -- savings or checking
num varchar,
description varchar,
debit varchar,
credit varchar,
UNIQUE(dt, account, description, debit, credit)
);
