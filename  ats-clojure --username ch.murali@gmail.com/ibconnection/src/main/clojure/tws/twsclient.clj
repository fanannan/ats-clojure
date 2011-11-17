(ns tws.twsclient
  (:import (com.ib.client EClientSocket Contract))
  (:import (wrapper.ewrapperimpl Ewrapperimpl))
  (:import (com.ib.client EClientSocket Contract))
  (:import (ewrapperimpl Ewrapperimpl))
  )
(def clientSocket (EClientSocket. (Ewrapperimpl.)))
(. clientSocket eConnect "localhost" 7496 3)
(def isConnected (. clientSocket isConnected))
(print "isConnected" isConnected)
(. clientSocket setServerLogLevel 5)
(. clientSocket reqAccountUpdates true "DU15237")
(. clientSocket reqCurrentTime)

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
(. clientSocket reqHistoricalData 1  c  "20100507 12:00:00"  "3600 S"  "15 secs"  "TRADES"  0 1)

