(ns extreme-modular.system
  "Components and their dependency relationships"
  (:refer-clojure :exclude (read))
  (:require
   [clojure.java.io :as io]
   [clojure.tools.reader :refer (read)]
   [clojure.string :as str]
   [clojure.tools.reader.reader-types :refer (indexing-push-back-reader)]
   [com.stuartsierra.component :refer (system-map system-using using)]
   [modular.maker :refer (make)]
   [extreme-modular.extreme-api :as api]
   [modular.bidi :refer (new-router)]
   [modular.http-kit :refer (new-webserver)]))

(defn ^:private read-file
  [f]
  (read
   ;; This indexing-push-back-reader gives better information if the
   ;; file is misconfigured.
   (indexing-push-back-reader
    (java.io.PushbackReader. (io/reader f)))))

(defn ^:private config-from
  [f]
  (if (.exists f)
    (read-file f)
    {}))

(defn ^:private user-config
  []
  (config-from (io/file (System/getProperty "user.home") ".extreme-modular.edn")))

(defn ^:private config-from-classpath
  []
  (if-let [res (io/resource "extreme-modular.edn")]
    (config-from (io/file res))
    {}))

(defn config
  "Return a map of the static configuration used in the component
  constructors."
  []
  (merge (config-from-classpath)
         (user-config)))


(defn http-listener-components
  [system config]
  (assoc system
    :http-listener-listener
    (->
      (new-webserver :port 8080)
      (using []))))

(defn modular-bidi-router-components
  [system config]
  (assoc system
    :modular-bidi-router-webrouter
    (->
      (make new-router config)
      (using []))))

(defn extreme-api-components [system config]
  (assoc system
    :extreme-api-api
    (-> (make api/new-api config)
        (using []))))

(defn new-system-map
  [config]
  (apply system-map
    (apply concat
      (-> {}
          
          (http-listener-components config)
          (modular-bidi-router-components config)
          (extreme-api-components config)))))

(defn new-dependency-map
  []
  {:http-listener-listener {:request-handler :modular-bidi-router-webrouter},
   :modular-bidi-router-webrouter {:api :extreme-api-api}})

(defn new-co-dependency-map
  []
  {})

(defn new-production-system
  "Create the production system"
  ([opts]
   (-> (new-system-map (merge (config) opts))
     (system-using (new-dependency-map))))
  ([] (new-production-system {})))
