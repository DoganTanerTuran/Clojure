(ns myrest.core
   (:use compojure.core)
   (:require [compojure.route :as route]
             [myrest.forside :as forside]))

(defroutes my_routes
           (GET "/ejendomme/:address" [address] (forside/value-address address))
           (route/resources "/"))
