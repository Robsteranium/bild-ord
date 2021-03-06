(ns bild-ord.app
  (:require [bidi.bidi :as bidi]
            bild-ord.handlers
            [bild-ord.routes :as routes]
            bild-ord.subs
            [bild-ord.views :as views]
            [re-frame.core :refer [dispatch]]
            [reagent.core :as reagent]))

(defn mount-root
  []
  (.log js/console "Mounting root")
  (reagent/render [views/app] (.getElementById js/document "app")))

(defn ^:export main []
  (dispatch [:initialise-db])
  (routes/init)
  (mount-root))
