(ns giggin.components.search
  (:require [reagent.core :as r]
            [giggin.state :as state]
            [giggin.utils.helpers :refer [find-address]]
            [giggin.utils.helpers :refer [get-address-value]]
            [giggin.utils.helpers :refer [print-test]]
            [giggin.utils.helpers :refer [rand-str]]))



(defn hello-button [atom address]
      #(reset! atom "new value")
      (let [hello (str "hello " address)]
           (js/alert hello)))




(defn info[address]
      (let [advalue (r/atom (#(get-address-value address)))]
           [:tr
            [:td address]
            [:td [:button.btn {:on-click #(reset! advalue (rand-str 4))}  "Recalculate value"]]
            [:td @advalue]]))




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
          [:div.h1 "Addresses:"]
          [:table.table
           [:thead
            [:tr
             [:th.title "Address:"]
             [:th.title "Actions"]
             [:th.title "Value:"]]]
           [:tbody
            (for [x @state/addressFound]
                 (info x))]]])])






















