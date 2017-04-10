
-- :name insert-transaction :! :n
insert into transaction (dt, account, num, description, debit, credit)
values ( to_date( :dt , 'MM/dd/yyyy' ), :account, :num, :description, :debit, :credit)



