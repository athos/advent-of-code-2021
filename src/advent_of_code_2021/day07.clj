(ns advent-of-code-2021.day07
  (:require [clojure.java.math :as math]
            [clojure.string :as str]))

(defn- parse-input [s]
  (->> (str/split s #",")
       (map parse-long)
       frequencies
       (into (sorted-map))))

(defn- constant-cost [^long from ^long to]
  (math/abs (- to from)))

(defn- progressive-cost [^long from ^long to]
  (let [n (math/abs (- to from))]
    (/ (* n (inc n)) 2)))

(defn- total-cost [cost-fn freqs pos]
  (reduce-kv (fn [sum p n] (+ sum (* n (cost-fn p pos)))) 0 freqs))

(defn- solve [cost-fn input]
  (let [freqs (parse-input input)]
    (->> (range (key (first freqs)) (key (first (rseq freqs))))
         (map (partial total-cost cost-fn freqs))
         (apply min))))

(defn part1 [input]
  (solve constant-cost input))

(defn part2 [input]
  (solve progressive-cost input))
