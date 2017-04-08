# bank-data




 (sql/insert-transaction db/db {:dt "1" :account "2" :num "3" :description "4" :debit "5" :credit "6"})



bank-data.core=> (require '[clj-time.core :as t])
nil
bank-data.core=> (require '[bank-data.sql :as sql])
nil
bank-data.core=> (require '[bank-data.db :as db])
nil
bank-data.core=> (sql/insert-transaction db/db {:dt (.toDate (t/date-time 2002 2 3)) :account "2" :num "3" :description "I POOPED" :debit "123" :credit "123"})
1

