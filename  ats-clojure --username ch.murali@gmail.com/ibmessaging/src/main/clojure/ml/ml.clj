(ns ml.ml
  (:import (com.hazelcast.core ITopic Hazelcast MessageListener) )
  )

(defrecord Mylistener []
com.hazelcast.core.MessageListener
(onMessage [this obj] 
           (println "listeneer received message" obj)
           )
)

(defrecord Mylistener2 []
com.hazelcast.core.MessageListener
(onMessage [this obj] 
           (println "listeneer2  received message" obj)
           )
)
