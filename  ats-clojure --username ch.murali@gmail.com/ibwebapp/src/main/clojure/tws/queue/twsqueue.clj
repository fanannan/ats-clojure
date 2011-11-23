(ns tws.queue.twsqueue
  (:use [clojure.tools.logging :only (info error debug)])
  (:import (ats.constants Constants))
  )

(debug "tws queue....")

(def twsqueue (. Hazelcast (getQueue Constants/TWSQUEUENAME)))

(while true 
  (def message (twsqueue))
  
  )






