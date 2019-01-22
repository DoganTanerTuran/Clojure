(defproject myrest "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :plugins [[lein-ring "0.8.7"]]
  :ring {:handler myrest.core/my-routes
         :auto-reload? true
         :auto-refresh? false}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [ring/ring "1.2.0"]
                 [compojure "1.2.0-SNAPSHOT"]
                 [clj-http "0.7.7"]
                 [hiccup "1.0.4"]
                 [org.clojure/data.json "0.2.6"]
                 [cheshire "5.8.1"]]
  :main myrest.core)
