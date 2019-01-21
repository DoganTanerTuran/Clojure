(ns giggin.core
  (:require [reagent.core :as r]
            [giggin.components.header :refer [header]]
            [giggin.components.search :refer [search]]
            [giggin.components.footer :refer [footer]]))

(defn app
  []
  [:div.container
   [header]
   [search]
   [footer]])

(defn ^:export main
  []
  (r/render
    [app]
    (.getElementById js/document "app")))
