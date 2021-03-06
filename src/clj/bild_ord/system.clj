(ns bild-ord.system
  (:require [clojure.java.io :as io]
            [com.stuartsierra.component :as component]
            [duct.component.endpoint :refer [endpoint-component]]
            [duct.component.handler :refer [handler-component]]
            [duct.component.hikaricp :refer [hikaricp]]
            [duct.component.ragtime :refer [ragtime]]
            [duct.middleware.not-found :refer [wrap-not-found]]
            [duct.middleware.route-aliases :refer [wrap-route-aliases]]
            [meta-merge.core :refer [meta-merge]]
            [ring.component.jetty :refer [jetty-server]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.webjars :refer [wrap-webjars]]
            [bild-ord.endpoint.game :refer [game-endpoint]]))

(def base-config
  {:app {:middleware [[wrap-not-found :not-found]
                      [wrap-webjars]
                      [wrap-defaults :defaults]
                      [wrap-route-aliases :aliases]]
         :not-found  (io/resource "bild_ord/errors/404.html")
         :defaults   (meta-merge site-defaults {:static {:resources "bild_ord/public"}})
         :aliases    {}}
   :ragtime {:resource-path "bild_ord/migrations"}})

(defn new-system [config]
  (let [config (meta-merge base-config config)]
    (-> (component/system-map
         :app  (handler-component (:app config))
         :http (jetty-server (:http config))
         :game-endpoint (endpoint-component game-endpoint)
         ;; :db   (hikaricp (:db config))
         ;; :ragtime (ragtime (:ragtime config))
         )
        (component/system-using
         {:http          [:app]
          :app           [:game-endpoint]
          :game-endpoint []
          ;; :ragtime [:db]
          }))))
