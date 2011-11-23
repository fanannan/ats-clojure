(ns ml.ml
  (:import (com.hazelcast.core ITopic Hazelcast MessageListener) )
  (:use [clojure.tools.logging :only (info error debug)])
 )

(defrecord Mylistener []
com.hazelcast.core.MessageListener
(onMessage [this obj] 
          ; (debug "listeneer received message" obj)
           )
)

(defrecord Mylistener2 []
com.hazelcast.core.MessageListener
(onMessage [this obj] 
           (debug "listeneer2  received message" obj)
           )
)
