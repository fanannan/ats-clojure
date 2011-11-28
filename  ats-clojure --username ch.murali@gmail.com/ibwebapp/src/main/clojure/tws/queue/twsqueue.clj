(ns tws.queue.twsqueue
  (:use [clojure.tools.logging :only (info error debug)])
  (:import [com.hazelcast.core IQueue Hazelcast])
  
  )
(def *TWS_QUEUE* "TWSQUEUE")
(info "tws queue....")

;(def twsqueue (. Hazelcast (getQueue *TWS_QUEUE*)))
(info "tws queue creation done")






