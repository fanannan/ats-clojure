(ns tws.twsclient
  (:import (com.ib.client EClientSocket))
  (:import (com.ibbase SimpleWrapper))
  )
(def clientSocket (. EClientSocket SimpleWrapper))
