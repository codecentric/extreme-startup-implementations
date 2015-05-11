(ns es-test.core
  (:require [clojure.math.numeric-tower :as math]))

(defn identify-question [question]
  (cond
    (re-matches #"^which of the following is an anagram .*$" question) :anagram
    (re-matches #"^what is (\d*) plus (\d*)$" question) :plus
    (re-matches #"^what is (\d*) plus (\d*) plus (\d*)$" question) :plus
    (re-matches #"^what is (\d*) multiplied by (\d*) plus (\d*)$" question) :multplus
    (re-matches #"^what is (\d*) plus (\d*) multiplied by (\d*)$" question) :plusmult
    (re-matches #"^what is (\d*) to the power of (\d*)$" question) :power 
    (re-matches #"^what is (\d*) minus (\d*)$" question) :minus
    (re-matches #"^what is the english scrabble score of (.*)$" question) :scrabble
    (re-matches #"^which of the following numbers is the largest: .*$" question) :largest
    (re-matches #"^what is (\d*) multiplied by (\d*)$" question) :multiplied
    (re-matches #"^which of the following numbers is both a square and a cube: .*$" question) :square-cube
    (re-matches #"^who is the Prime Minister of Great Britain$" question) :prime-minister
    (re-matches #"^what colour is a banana" question) :banana
    (re-matches #"^what currency .*$" question) :currency
    (re-matches #"^which city is the Eiffel .*$" question) :eiffel
    (re-matches #"^who played James Bond .*$" question) :bond
    (re-matches #"^which of the following numbers are primes: .*" question) :primes
    (re-matches #"^what is the .* number in the Fibonacci sequence" question) :fibonacci
    :else :unknown))

(def scrabble-scores
  {\a 1 \b 3 \c 3 \d 2 \e 1 \f 4
   \g 2 \h 4 \i 1 \j 8 \k 5 \l 1
   \m 3 \n 1 \o 1 \p 3 \q 10 \r 1
   \s 1 \t 1 \u 1 \v 4 \w 4 \x 8
   \y 4 \z 10})

(defn extract-numbers [question]
  (let [groups (re-seq #"\d+" question)
        numbers (map (fn [s] (Integer/valueOf s)) groups)]
    numbers))

(defn extract-scrabbel-word [question]
  (let [word (second (re-matches #"^what is the english scrabble score of (.*)$" question))]
    word))

(defn extract-anagram-words [question]
  (let [groups (re-seq #"^.*\"(.*)\": (.*)$" question)
        word (second (first groups))
        candidates (map #(.trim %) (into [] (.split (nth (first groups) 2) ",")))]
    [word candidates]))

(defn is-anagram? [word candidate]
  (= (frequencies word) (frequencies candidate)))

(defn fib [a b] (cons a (lazy-seq (fib b (+ b a)))))

(defn sieve [s]
  (cons (first s)
        (lazy-seq (sieve (filter #(not= 0 (mod % (first s)))
                                 (rest s))))))

(def cubes (map #(* % % %) (iterate inc 1)))

(def square-cubes (filter #(zero? (second (math/exact-integer-sqrt %))) cubes))

(defn is-square-cube? [number] (= (first (drop-while #(< % number) square-cubes)) number))

(def handle-question nil)
(defmulti handle-question identify-question)

(defmethod handle-question :anagram [question]
  (let [[word candidates] (extract-anagram-words question)
        is-anagram-fn (partial is-anagram? word)
        results (filter is-anagram-fn candidates)]
    (apply str (interpose ", " results))))

(defmethod handle-question :prime-minister [question]
  "David Cameron")

(defmethod handle-question :eiffel [question]
  "Paris")

(defmethod handle-question :bond [question]
  "Sean Connery")

(defmethod handle-question :currency [question]
  "Peseta")

(defmethod handle-question :banana [question]
  "Yellow")

(defmethod handle-question :scrabble [question]
  (let [word (extract-scrabbel-word question)]
    (str (reduce + (map #(get scrabble-scores %) word)))))

(defmethod handle-question :power [question]
  (let [numbers (extract-numbers question)]
    (str (long (Math/pow (first numbers) (second numbers))))))

(defmethod handle-question :multplus [question]
  (let [numbers (extract-numbers question)]
    (str (+ (* (first numbers) (second numbers)) (nth numbers 2)))))

(defmethod handle-question :plusmult [question]
  (let [numbers (extract-numbers question)]
    (str (+ (* (second numbers) (nth numbers 2)) (first numbers)))))

(defmethod handle-question :square-cube [question]
  (let [numbers (extract-numbers question)
        square-cube (first (filter is-square-cube? numbers))]
    (str square-cube)))

(defmethod handle-question :primes [question]
  (let [numbers (extract-numbers question)
        highest-number (apply max numbers)
        prime-set (set (take highest-number (sieve (iterate inc 2))))
        primes (sort (into [] (clojure.set/intersection prime-set (set numbers))))
        response (apply str (interpose ", " primes))]
    (str response)))

(defmethod handle-question :fibonacci [question]
  (let [numbers (extract-numbers question)
        number (first numbers)
        result (last (take number (fib 1 1)))]
    (str result)))

(defmethod handle-question :multiplied [question]
  (let [numbers (extract-numbers question)]
    (str (reduce * numbers))))

(defmethod handle-question :largest [question]
  (let [numbers (extract-numbers question)]
    (str (apply max numbers))))

(defmethod handle-question :plus [question]
  (let [numbers (extract-numbers question)]
    (str (reduce + numbers))))

(defmethod handle-question :minus [question]
  (let [numbers (extract-numbers question)]
    (str (reduce - numbers))))

(defmethod handle-question :unknown [question]
  (do
    (println question)
    "Nils"))

(defn handler [request]
  (let [params (:params request)
        q (get params "q")
        trimmed-q (subs q 10)
        response (handle-question trimmed-q)]
    (println "Question: " q)
    (println "Answer: " response)
    (println "------------------------------")
    {:status 200,
     :body response}))
