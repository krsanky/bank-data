(ns bank-data.db
  (:require 
        [clojure.java.jdbc :as jdbc]
        [clj-time.coerce :as c])
  (:import 
        org.postgresql.util.PGobject
        java.sql.Array
        clojure.lang.IPersistentMap
        clojure.lang.IPersistentVector
        [java.sql
            BatchUpdateException
            Date
            Timestamp
            PreparedStatement]))

(def db
  {:classname "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname "//localhost/bankd"
   :user "bankd"
   :password "horsecavebanana" })

