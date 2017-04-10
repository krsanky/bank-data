(ns bank-data.data
    (:require 
        [clojure.java.io :as io]
        [bank-data.sql :as sql]
        [bank-data.db :as db]
        [clojure-csv.core :as csv]))

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


