(ns script.startup
 (:require [wrapper.ewrapperimpl])
 (:use tws.client.twsclient)
 (:use constants.constants)
  ;"require should come before import for def record imports"
  (:import [wrapper.ewrapperimpl Ewrapperimpl])
 ;(:import (com.thoughtworks.xstream XStream))
  (:use [clojure.tools.logging :only (info error)])
  (:use [clojure.java.shell :only (sh)])

  )

;start tws



;(sh "javaws" "/home/mchapala/Downloads/edemo.jnlp &")


;(. Thread (sleep 60000))
(info "comes to connection")
; connect tws
(def connObj (connect (Ewrapperimpl.) "localhost" 7496 4))

;(.reqHistoricalData connObj 1  (get contracts 3)  "20100507 12:00:00"  "3600 S"  "15 secs"  "TRADES"  0 1)


;(def sampleContractToTest (.invoke createContract "BAC"))

(def sampleContractTotest (createContract "BAC" "STK" "SMART" "" "" "" "" "USD"))
(info "sampleContractTotest " (.toXML xstreamInstance sampleContractTotest))
;(request-market-data connObj 1 sampleContractToTest  "221")



;(info "tws queue...while true")
;(while true 
;  (def message (.take twsqueue))
;  (info "message" message)
;  )


; create hazelcast
;
