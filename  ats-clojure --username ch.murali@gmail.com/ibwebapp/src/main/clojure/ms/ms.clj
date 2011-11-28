(ns ms.ms
  (:import (com.hazelcast.core ITopic Hazelcast MessageListener))
  (:require [ml.ml])
  (:import [ml.ml Mylistener  Mylistener2])
  (:use [clojure.tools.logging :only (info error debug)])
  )
(info "sender")

(info "creating topic")
(def topic (. Hazelcast (getTopic  "default") ))


(info "creating listener")
(def mL (Mylistener. ))
(info "adding topic to msg listener")
(. topic  (addMessageListener mL))


(info "creating topic1")
(def topic1 (. Hazelcast (getTopic  "default") ))
(def mL2 (Mylistener2. ))


(. topic1  (addMessageListener mL2))
(. topic  (addMessageListener mL2))

(info "publishing msg")
(. topic1 (publish "topic1 my-message-object"))

(info "publishing msg")
(. topic (publish "topic my-message-object"))
  
		
