(ns ms.ms
  (:import (com.hazelcast.core ITopic Hazelcast MessageListener))
  
  (:require (ml.Mylistener))
  )
(print "sender")
(. Hazelcast (getTopic  "default") )
(def topic (. Hazelcast (getTopic  "default") ))
(def mL (ml.ml.Mylistener. ))
(. topic  (addMessageListener mL))




(def topic1 (. Hazelcast (getTopic  "default") ))
(def mL2 (ml.ml.Mylistener2. ))

(. topic1  (addMessageListener mL2))
(. topic  (addMessageListener mL2))

(. topic1 (publish "topic1 my-message-object"))
(. topic1 (publish "topic1 my-message-object2"))
(. topic1 (publish "topic1 my-message-object3"))

(. topic (publish "topic my-message-object"))
(. topic (publish "topic my-message-object2"))
(. topic (publish "topic my-message-object3"))
  
		
