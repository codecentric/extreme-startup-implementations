(defproject es-test "0.1.0-SNAPSHOT"
  :description "Testing my Extreme Startup setup"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [hiccup "1.0.4"]
                 [ring/ring "1.3.2"]
                 [org.clojure/math.numeric-tower "0.0.4"]]
  :profiles {:dev {:dependencies [[lein-ring "0.9.2"]
                                  [org.clojure/test.check "0.7.0"]]}}
  :plugins [[lein-ring "0.9.2"]]
  :ring {:handler es-test.core/handler
         :nrepl {:start? true}
         :auto-refresh? true
         :auto-reload? true
         :port 8000})
















