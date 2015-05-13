(defproject extreme-modular "0.1.0-SNAPSHOT"
  :description "A modular project created with lein new modular bidi-hello-world"
  :url "http://github.com/gerrithentschel/extreme-modular"

  :exclusions [com.stuartsierra/component]

  :dependencies
  [
   [com.stuartsierra/component "0.2.2"]
   [juxt.modular/bidi "0.9.1"]
   [juxt.modular/http-kit "0.5.4"]
   [juxt.modular/maker "0.5.0"]
   [juxt.modular/wire-up "0.5.0"]
   [org.clojure/clojure "1.7.0-beta2"]
   [org.clojure/tools.reader "0.8.13"]
   [prismatic/plumbing "0.3.5"]
   [prismatic/schema "0.3.5"]
   [cheshire "5.4.0"]
   ]

  :main extreme-modular.main

  :repl-options {:init-ns user
                 :welcome (println "Type (dev) to start")}

  :aliases {"gen" ["run" "-m" "extreme-modular.generate"]}

  :profiles {:dev {:dependencies [[org.clojure/tools.namespace "0.2.5"]
                                  [alembic "0.3.2"]]
                   :source-paths ["dev"
                                  ]}})
