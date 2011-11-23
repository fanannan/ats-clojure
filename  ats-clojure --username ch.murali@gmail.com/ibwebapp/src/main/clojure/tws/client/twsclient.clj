(ns tws.client.twsclient
  (:import (com.ib.client EClientSocket Contract))
  (:require [wrapper.ewrapperimpl])
  ;"require should come before import for def record imports"
  (:import [wrapper.ewrapperimpl Ewrapperimpl])
  )


(defn connect
([wrapper] (connect wrapper "localhost" 7496 1))
([wrapper host] (connect wrapper host 7496 1))
([wrapper host port] (connect wrapper port 1))
([wrapper host port client-id]
(let [connection (EClientSocket. wrapper)]
(doto connection
(.eConnect host port client-id)
(.setServerLogLevel 5)
(.reqCurrentTime))))
)



(defn createContract
    ([securityName]
       (doto (Contract. )
          (:m_symbol securityName)
          (:m_secType "STK")
          (:m_exchange "SMART")
          (:m_expiry "")
          (:m_right "")
          (:m_multiplier "")
          (:m_primaryExch "")
          (:m_currency "USD")
          )
      )
      )

(def c (createContract "BAC"))
;(print c :m_symbol c :m_exchnage)
(.(connect (Ewrapperimpl.)) reqHistoricalData 1  c  "20100507 12:00:00"  "3600 S"  "15 secs"  "TRADES"  0 1)


(defn disconnect
"Call this function to terminate the connections with TWS.
Calling this function does not cancel orders that have already been sent."
[connection]
(.eDisconnect connection))

