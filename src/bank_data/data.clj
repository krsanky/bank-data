(ns bank-data.data
    (:require 
        [bank-data.sql :as sql]
        [bank-data.db :as db]
        [clojure-csv.core :as csv]))

;; bank-data.core=> (sql/insert-transaction db/db {:dt (.toDate (t/date-time 2002 2 3)) :account "2" :num "3" :description "I POOPED" :debit "123" :credit "123"})
;; 
;; (f/parse bdata/dfmtr "02/2/2001")
;; #object[org.joda.time.DateTime 0x3d7fc98 "2001-02-02T00:00:00.000Z"]

;; PSQLException ERROR: duplicate key value violates unique constraint
(defn easy-insert [cols]
    (sql/insert-transaction db/db 
        {
            :dt (first cols) ;; KISS: let postgres decode the string to a date
            :account "testing"
            :num (nth cols 1)
            :description (nth cols 2)
            :debit (nth cols 3)
            :credit (nth cols 4)}))

(defn read-file-to-db [file]
    (println (str "read: " file " ..."))
    (let [csv-data (rest (csv/parse-csv (slurp file)))] ;; 1st line is headers
        (for [t csv-data]
            (try 
                (easy-insert t)
                (catch Exception e (str "caught exception: " (.getMessage e)))))))

