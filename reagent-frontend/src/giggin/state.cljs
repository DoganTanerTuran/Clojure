(ns giggin.state
  (:require [reagent.core :as r]))

(def address (r/atom {:value nil}))

(def addressFound (r/atom []))

(def forcer (r/atom 0))


