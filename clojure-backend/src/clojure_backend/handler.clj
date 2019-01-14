(ns clojure-backend.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [ring.util.http-status :as http-status]
            [schema.core :as s]))

;; Schemas

(s/defschema Calculation
  {:id s/Int
   :name s/Str
   (s/optional-key :description) s/Str
   :calculation s/Str})

(s/defschema NewCalculation (dissoc Calculation :id))
(s/defschema UpdatedCalculation NewCalculation)

;; Database

(def calculations (atom {}))

(let [ids (atom 0)]
  (defn update-calculation! [maybe-id maybe-calculation]
    (let [id (or maybe-id (swap! ids inc))]
      (if maybe-calculation
        (swap! calculations assoc id (assoc maybe-calculation :id id))
        (swap! calculations dissoc id))
      (@calculations id))))

;; Application

(def app
  (api
    {:swagger
     {:ui "/"
      :spec "/swagger.json"
      :data {:info {:title "API"
                    :description "Calculation"
                    :contact {:url "https://localhost:5000/api/"}}
             :tags [{:name "pizza", :description "pizzas"}]}}}

    (context "/api/" []
      (resource
        {:tags ["api"]
         :get {:summary "API"
               :description "Calculation API"
               :responses {http-status/ok {:schema [Calculation]}}
               :handler (fn [_] (ok (vals @calculations)))}
         :post {:summary "add's a calculation"
                :parameters {:body-params NewCalculation}
                :responses {http-status/created {:schema Calculation
                                                 :description "the created calculation"
                                                 :headers {"Location" s/Str}}}
                :handler (fn [{body :body-params}]
                           (let [{:keys [id] :as calculation} (update-calculation! nil body)]
                             (created (path-for ::calculation {:id id}) calculation)))}}))

    (context "/calculation/:id" []
      :path-params [id :- s/Int]

      (resource
        {:tags ["calculation"]
         :get {:x-name ::calculation
               :summary "gets a calculation"
               :responses {http-status/ok {:schema Calculation}}
               :handler (fn [_]
                          (if-let [calculation (@calculations id)]
                            (ok calculation)
                            (not-found)))}
         :put {:summary "updates a calculation"
               :parameters {:body-params UpdatedCalculation}
               :responses {http-status/ok {:schema Calculation}}
               :handler (fn [{body :body-params}]
                          (if (@calculations id)
                            (ok (update-calculation! id body))
                            (not-found)))}
         :delete {:summary "deletes a calculation"
                  :handler (fn [_]
                             (update-calculation! id nil)
                             (no-content))}}))))