(ns bank-data.data
    (:require 
        [clojure.java.io :as io]
        [bank-data.sql :as sql]
        [bank-data.db :as db]
        [clojure-csv.core :as csv]))

;; bank-data.core=> (sql/insert-transaction db/db {:dt (.toDate (t/date-time 2002 2 3)) :account "2" :num "3" :description "I POOPED" :debit "123" :credit "123"})
;; 
;; (f/parse bdata/dfmtr "02/2/2001")
;; #object[org.joda.time.DateTime 0x3d7fc98 "2001-02-02T00:00:00.000Z"]

;; PSQLException ERROR: duplicate key value violates unique constraint
(defn easy-insert [cols account]
    (sql/insert-transaction db/db 
        {
            :dt (first cols) ;; KISS: let postgres decode the string to a date
            :account account
            :num (nth cols 1)
            :description (nth cols 2)
            :debit (nth cols 3)
            :credit (nth cols 4)}))

(defn read-file-to-db [file account]
    (println (str "read: " file " ..."))
    (let [csv-data (rest (csv/parse-csv (slurp file)))] ;; 1st line is headers
        (for [t csv-data]
            (try 
                (easy-insert t account)
                (catch Exception e (str "caught exception: " (.getMessage e)))))))

(defn read-dir-to-db [dir account]
    (for [f (.list (io/file dir))]
        (read-file-to-db (str dir "/" f) account)))



;; bank-data.core=> (.isDirectory (io/file "nogit/savings"))
;; true
;; bank-data.core=> (.list (io/file "nogit/savings"))
;; #object["[Ljava.lang.String;" 0x63fbb4e1 "[Ljava.lang.String;@63fbb4e1"]
;; bank-data.core=> (first (.list (io/file "nogit/savings")))
;; "transactions(1).CSV"
;; bank-data.core=> (first (.listFiles (io/file "nogit/savings")))
;; #object[java.io.File 0x4fc38cbe "nogit/savings/transactions(1).CSV"]

