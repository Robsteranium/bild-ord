(ns bild-ord.features.progress-tracking
  (:require [clojure.test :refer :all]
            [clj-webdriver.taxi :as t]
            [bild-ord.features.fixtures :refer :all]
            [bild-ord.features.helper :refer :all]))

(use-fixtures :once with-built-cljs with-server with-browser)

(deftest progress-tracking-test
  (testing "Completing a game turns the index marker green"
    (log-in)
    (is (t/exists? {:css ".todo", :text "1"}))
    (start-game 1)
    (drag-words "sol" "ros" "vas" "ram" "sil")
    (click-on "Gå vidare")
    (type-words "sol" "ros" "vas" "ram" "sil")
    (click-on "Gå vidare")
    (is (t/exists? {:css ".done", :text "1"}))))
