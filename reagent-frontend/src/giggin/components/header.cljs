(ns giggin.components.header)

(defn header
  []
  [:header
   [:img.logo {:src "img/Visma-logo.png" :alt "Giggin logo" :style {:margin-top 40}}]
   [:h3 {:style {:margin-top 10}} "Visma Ratings"]])
