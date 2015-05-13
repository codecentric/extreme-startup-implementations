(ns extreme-modular.extreme-api
  (:require [bidi.bidi :as bidi]
            [modular.ring :refer [WebRequestHandler]]
            [ring.middleware.params :as rm]
            [ring.middleware.keyword-params :as kp]
            [modular.bidi :refer (as-request-handler)]
            [bidi.ring :refer (redirect)]
            [clojure.string :as str]
            [clojure.tools.logging :as log]))

(defn largest-number [numbers]
  (str (apply max (map #(Integer/parseInt (.trim %)) (str/split numbers #",")))))

(defn wrap-handler [k handle-fn]
  (bidi/handler k (-> handle-fn
                      kp/wrap-keyword-params
                      rm/wrap-params)))

(defn add [nums]
  (apply + nums))

(defn parse-nums [regex question]
  (map #(Integer/parseInt %) (drop 1 (re-find regex question))))

(defn mult [nums]
  (apply * nums))

(defn minus [nums]
  (apply - nums))

(defn response [result]
  {:status 200 :body (str result)})

(def squares (map (fn [x] (* x x)) (drop 2 (range))))
(def cubes (map (fn [x] (* x x x)) (drop 2 (range))))

(defn square-cube [nums]
  (str/join ", "
            (filter (fn [n1]
                      (and (= n1 (first (drop-while (fn [x] (< x n1)) squares)))
                           (= n1 (first (drop-while (fn [x] (< x n1)) cubes)))))
                    nums)))

(defn prime? [n]
  (= 0 (count (filter (fn [x] (= 0 (rem n x))) (range 2 n)))))

(defn primes [nums]
  (str/join ", " (filter prime? nums)))

(defn as-nums [ss]
  (map #(Integer/parseInt %) (keep #(.trim %) (str/split ss #","))))

(def fibs (map second (iterate (fn [[n0 n1]]
                                 [n1 (+ n0 n1)])
                               [0 1])))

(defn fibn [[n]]
  (nth fibs (dec n)))

(defn power [[n m]]
  (long (Math/pow n m)))

(defn multplus [[x y z]]
  (+ (* x y) z ))

(defn anagram [source candidates]
  (let [cand->sorted-chars (map (juxt identity (fn [xs] (sort (seq xs))))
                                candidates)
        source-chars (sort (seq source))]
    (first (keep (fn [[c cs]]
                   (when (= cs source-chars)
                     c))
                 cand->sorted-chars))))

(def scrabble-score {"A" 1 "B" 3 "C" 3 "D" 2 "E" 1 "F" 4 "G" 2 "H" 4 "I" 1 "J" 8 "K" 5 "L" 1 "M" 3 "N" 1 "O" 1 "P" 3 "Q" 10 "R" 1 "S" 1 "T" 1 "U" 1 "V" 4 "W" 4 "X" 8 "Y" 4 "Z" 10})

(defn scrabble [w]
  (reduce + (map #(get scrabble-score (str %)) (.toUpperCase w))))

(defn question-handler [req]
  (let [qparam (get-in req [:params :q])
        [_ question ss] (str/split qparam #":")]
    (log/info {:question qparam})

    (cond (.contains question "which of the following numbers is the largest")
          {:status 200 :body (largest-number (last (str/split qparam #":")))}
          (.contains question "what is the english scrabble score of")
          (response (scrabble (second (re-find #"what is the english scrabble score of (.*)" question))))
          (.contains question "banana")
          (response "Yellow")
          (re-find #"which of the following is an anagram of \"(.*)\"" question)
          (response (anagram (second (re-find #"which of the following is an anagram of \"(.*)\"" question))
                             (as-nums ss)))
          (re-find #"what is (.*) multiplied by (.*) plus (.*)" question)
          (response (multplus (parse-nums #"what is (.*) multiplied by (.*) plus (.*)" question)))
          (re-find #"what is (.*) to the power of (.*)" question)
          (response (power (parse-nums #"what is (.*) to the power of (.*)" question)))
          (re-find #"what is (.*) plus (.*) plus (.*)" question)
          {:status 200 :body (str (add (parse-nums #"what is (.*) plus (.*) plus (.*)" question)))}
          (re-find #"what is (.*) plus (.*)" question)
          {:status 200 :body (str (add (parse-nums #"what is (.*) plus (.*)" question)))}
          (re-find #"who played James Bond in the film Dr No" question)
          (response "Sean Connery")
          (re-find #"what is the (.*)(st|nd|th) number in the Fibonacci sequence: " question)
          (response (fibn (parse-nums #"what is the (.*).. number in the Fibonacci sequence" question)))
          (re-find #"who is the Prime Minister of Great Britain" question)
          (response "David Cameron")
          (re-find #"what currency did Spain use before the Euro" question)
          (response "Peseta")
          (re-find #"what is (.*) multiplied by (.*)" question)
          (response (mult (parse-nums #"what is (.*) multiplied by (.*)" question)))
          (re-find #"what is (.*) minus (.*)" question)
          (response (minus (parse-nums #"what is (.*) minus (.*)" question)))
          (re-find #"which of the following numbers are primes" question)
          (response (primes (as-nums ss)))
          (re-find #"which of the following numbers is both a square and a cube" question)
          (response (square-cube (as-nums ss)))
          :else
          {:status 200 :body "gerrit"})))

(defrecord Api []
  bidi/RouteProvider
  (routes [_]
    ["/" (wrap-handler ::api question-handler)])
  WebRequestHandler
  (request-handler [this] (as-request-handler this)))

(defn new-api []
  (->Api))