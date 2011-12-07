(ns script.startup
 (:use tws.client.twsclient) 
  (:require [wrapper.ewrapperimpl])
  ;"require should come before import for def record imports"
  (:import [wrapper.ewrapperimpl Ewrapperimpl])
  (:use [clojure.tools.logging :only (info error)])
  (:use [clojure.java.shell :only (sh)])
  )

;start tws


(info "Starting tws")
(info "Executing javaws /home/mchapala/Downloads/edemo.jnlp &")
;(sh "javaws" "/home/mchapala/Downloads/edemo.jnlp &")
(info "Started sleeping")
;(info "time" (. currentTimeMillis System))
(. Thread (sleep 60000))
(info "comes to connection")
; connect tws
(def connObj (connect (Ewrapperimpl.) "localhost" 7496 4))
(info "requesting historical data")


;(.reqHistoricalData connObj 1  (get contracts 3)  "20100507 12:00:00"  "3600 S"  "15 secs"  "TRADES"  0 1)
(info "requesting historical data.done")

(info "requesting market Data")

(request-market-data connObj 1  (get contracts 3) "221")

                 
;(info "tws queue...while true")
;(while true 
;  (def message (.take twsqueue))
;  (info "message" message)
;  )


; create hazelcast
;
