(ns myrest.forside
  (:require [clj-http.client :as http])
  (:use hiccup.page hiccup.element))


(defn index-ejendomme []
  (html5
    [:html]
    [:head]
    [:body (:body (http/get "https://dawa.aws.dk/adresser?vejnavn=R%C3%B8dkildevej&husnr=46&struktur=mini"))]
    ))
