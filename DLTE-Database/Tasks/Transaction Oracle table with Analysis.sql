--1.Filter based on given range of date
create view filter_date as select * from transaction where transaction_date between '01-04-24' and '01-05-24';
select * from filter_date;

--2.least amount transfered 
create view least_amount as select min(transaction_amount) as min_amount from transaction;
select * from least_amount;

--3.Maximum amount transfered
create view max_amount as select max(transaction_amount) as max_amount from transaction;

--4.Number of transaction made to a perticular beneficiary
create view count_transaction as select transaction_to, count(*) as count_beneficiary from transaction group by transaction_to;
select * from count_transaction;

--5.Filter Based on particular remarks
create view remark_filter as select transaction_id,transaction_date,transaction_to,transaction_amount from transaction where transaction_remarks = 'family';
select * from remark_filter;