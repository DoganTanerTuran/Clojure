(ns giggin.components.search
  (:require [reagent.core :as r]
            [giggin.state :as state]
            [giggin.utils.helpers :refer [find-address]]
            [giggin.utils.helpers :refer [get-address-value]]
            [giggin.utils.helpers :refer [change-value]]
            [giggin.utils.helpers :refer [rand-str]]))



(defn hello-button [atom address]
      #(reset! atom "new value")
      (let [hello (str "hello " address)]
           (js/alert hello)))




(defn info[input]
      [:tr {:key (rand-str 4)}
       [:td  (get input 1)]
       [:td  (get input 3)]
       [:td [:button.btn {:style {:margin-left 40} :on-click #(change-value (get input 1)) } "Recalculate"]]])




(defn search
      []
      [:div {:style {:margin-top 40}}
       [:input.form__input {:type "text"
                            :on-change #(swap! state/address assoc :value (-> % .-target .-value))
                            :id "address-input"
                            :value (:value @state/address)}]
       [:button.btn {:style {:margin-left 40} :on-click #(find-address (:value @state/address))} "Search"]
       (if (empty? @state/addressFound)
         [:div.title "nothing found"]
         [:div.padtop
          [:div.h2 "Addresses:"]
          [:table.table {:style {:margin-top 40}}
           [:thead
            [:tr
             [:th.title "Address:"]
             [:th.title "Value:"]
             [:th.title "Actions:"]]]
           [:tbody
            (for [x @state/addressFound]
                 (info x))]]])])






















