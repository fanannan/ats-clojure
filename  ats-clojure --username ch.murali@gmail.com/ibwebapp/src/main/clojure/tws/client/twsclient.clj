(ns tws.client.twsclient
  (:import (com.ib.client EClientSocket Contract))
  (:require [wrapper.ewrapperimpl])
  ;"require should come before import for def record imports"
  (:import [wrapper.ewrapperimpl Ewrapperimpl])
  (:use [clojure.tools.logging :only (info error)])

  )


(defn connect
([wrapper] (connect wrapper "localhost" 7496 1))
([wrapper host] (connect wrapper host 7496 1))
([wrapper host port] (connect wrapper port 1))
([wrapper host port client-id]
(let [connection (EClientSocket. wrapper)]
(println "connecting to " host "port" port "clientid" client-id)
  (doto connection
(.eConnect host port client-id)
(.setServerLogLevel 5)
(.reqCurrentTime))))
)





(defn createContract
    (
      [securityName secType exchage expiry m_right multiplier primaryExch currency]
      (def c (Contract.))
      (doto (c)
          (:m_symbol securityName)
          (:m_secType secType)
          (:m_exchange exchage)
          (:m_expiry expiry)
          (:m_right m_right)
          (:m_multiplier multiplier)
          (:m_primaryExch primaryExch)
          (:m_currency currency)
          )
      )
    ([securityName]
       (createContract securityName "STK" "SMART" "" "" "" "" "USD")
      )
    ([securityName exchange]
       (createContract securityName "STK" exchange "" "" "" "" "USD")
      )
      )


(defn request-market-data
([connection id contract tick-list]
  (info "requesting market data for" contract)
(.reqMktData connection id contract tick-list false)
id
)

([connection id contract]
(.reqMktData connection id contract "" false)
id)
)



(defn disconnect
[connection]
(.eDisconnect connection))



;default lis of contracts 
;; modify using assoc
(def contracts {1 createContract "BAC"
                2 createContract "IBN"
                }
              )
