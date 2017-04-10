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


;;(extend-protocol jdbc/IResultSetReadColumn                                
;;    java.sql.Date
;;    (result-set-read-column [v _ _] (c/from-sql-date v)))
;;
;;(extend-type org.joda.time.DateTime                                       
;;    jdbc/ISQLParameter
;;    (set-parameter [v ^PreparedStatement stmt idx]
;;        (.setTimestamp stmt idx (c/to-sql-time v))))       

;;(defn to-date [^java.sql.Date sql-date]
;;  (-> sql-date (.getTime) (java.util.Date.)))
;;
;;(extend-protocol jdbc/IResultSetReadColumn
;;  Date
;;  (result-set-read-column [v _ _] (to-date v))
;;
;;  Timestamp
;;  (result-set-read-column [v _ _] (to-date v))
;;
;;  Array
;;  (result-set-read-column [v _ _] (vec (.getArray v)))
;;
;;  PGobject
;;  (result-set-read-column [pgobj _metadata _index]
;;    (let [type  (.getType pgobj)
;;          value (.getValue pgobj)]
;;      value)))

;;      (case type
;;        "json" (parse-string value true)
;;        "jsonb" (parse-string value true)
;;        "citext" (str value)
;;        value))))

;;(extend-type java.util.Date
;;  jdbc/ISQLParameter
;;  (set-parameter [v ^PreparedStatement stmt ^long idx]
;;    (.setTimestamp stmt idx (Timestamp. (.getTime v)))))


