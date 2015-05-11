(ns es-test.core-test
  (:require [clojure.test :refer :all]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :refer [defspec]]
            [es-test.core :refer :all]
            [clojure.math.numeric-tower :as math]))

(def square-cubes-gen (gen/fmap (fn [n] (first (take 1 (drop n square-cubes)))) gen/pos-int))

(defspec is-square-cube-is-true-for-square-cubes
  10
  (prop/for-all [sc square-cubes-gen]
                (true? (is-square-cube? sc))))












