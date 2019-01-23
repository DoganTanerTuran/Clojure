(ns giggin.utils.helpers
  (:require [giggin.state :as state]
            [clojure.string :as s]
            [reagent.core :as r])
  (:require [cljs.core.async :refer [<!]] [cljs-http.client :as http])
  (:require-macros [cljs.core.async.macros :refer [go]]))



(defn get-address-value
      [address]
      (str (+ (rand-int 1100000) 1000000) " kr."))


(defn find-address
      [address]
      (go (let [response (<! (http/get "https://my-json-server.typicode.com/nielsjes/vismaRatings/db"))]
               (reset! state/addressFound ())
               (doseq [x (map :street(:addresses (:body response)))]
                      (if (s/includes? (s/lower-case x) (s/lower-case address))
                        (swap! state/addressFound conj [:road x :value (get-address-value x)])))
               (println @state/addressFound))))






(defn rand-str [len]
      (apply str (take len (repeatedly #(char (+ (rand 26) 65))))))

(defn change-value
      [address]


      (let [new-address (r/atom ())]
        (doseq [x (reverse @state/addressFound)]
               (if (s/includes? (get x 1) address)
                 (swap! new-address conj [:road address :value (get-address-value x)])
                 (swap! new-address conj x)))
        (reset! state/addressFound @new-address)))


