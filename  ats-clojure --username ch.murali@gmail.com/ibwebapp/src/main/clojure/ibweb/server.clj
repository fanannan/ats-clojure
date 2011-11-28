(ns ibweb.server
  (:require [noir.server :as server]
            [ibweb.models :as models]))

(server/load-views "ibweb/views/")

(defn -main [& m]
  (let [mode (or (first m) :dev)
        port (Integer. (get (System/getenv) "PORT" "8080"))]
    (models/initialize)
    (server/start port {:mode (keyword mode)
                        :ns 'ibweb})))


