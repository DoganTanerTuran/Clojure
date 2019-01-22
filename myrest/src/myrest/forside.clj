(ns myrest.forside
    (:require [clj-http.client :as http])
    (:use hiccup.page hiccup.element)
    (:require [cheshire.core :as cheshire])
    (:require [clojure.data.json :as json]))

(def api (:body (http/get "https://my-json-server.typicode.com/nielsjes/vismaRatings/db")))


(defn value-address [address]
      (def result (filter #(.contains (:street %) address)  (:addresses (cheshire/parse-string api true))))
      (def parsedResult (cheshire/generate-string result))

      (html5
        [:html]
        [:head {"mlæømsdæfl:true"}]
        [:body parsedResult]))


