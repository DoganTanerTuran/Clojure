(ns giggin.utils.helpers
  (:require [giggin.state :as state])
  (:require [cljs.core.async :refer [<!]] [cljs-http.client :as http])
  (:require-macros [cljs.core.async.macros :refer [go]]))

(defn format-price
  [cents]
  (str " €" (/ cents 100)))

(defn find-address
      [address]
      (go (let [response (<! (http/get "https://my-json-server.typicode.com/nielsjes/vismaRatings/db"))]
               (swap! state/addressFound :addresses (:addresses (:body response))))))


(defn rand-str [len]
      (apply str (take len (repeatedly #(char (+ (rand 26) 65))))))

(defn get-address-value
      [address]
      (rand-str 4))

(defn print-test
      []
      (println "testtest"))