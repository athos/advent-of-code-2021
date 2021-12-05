(ns advent-of-code-2021.day05
  (:require [clojure.java.math :as math]))

(defn parse-segment [line]
  (let [[_ x1 y1 x2 y2] (re-matches #"(\d+),(\d+) -> (\d+),(\d+)" line)]
    [[(parse-long x1) (parse-long y1)]
     [(parse-long x2) (parse-long y2)]]))

(defn range* [s e]
  (cond (< s e) (range s (inc e))
        (> s e) (range s (dec e) -1)
        :else (repeat s)))

(defn draw-segment [m [[x1 y1] [x2 y2]]]
  (->> (map vector (range* x1 x2) (range* y1 y2))
       (reduce #(update %1 %2 (fnil inc 0)) m)))

(defn count-overlaps [m]
  (count (filter #(> % 1) (vals m))))

(defn problem1 [lines]
  (->> (map parse-segment lines)
       (filter (fn [[[x1 y1] [x2 y2]]]
                 (or (= x1 x2) (= y1 y2))))
       (reduce draw-segment {})
       count-overlaps))

(defn problem2 [lines]
  (->> (map parse-segment lines)
       (filter (fn [[[x1 y1] [x2 y2]]]
                 (or (= x1 x2) (= y1 y2)
                     (= (math/abs (/ (- y2 y1) (- x2 x1))) 1))))
       (reduce draw-segment {})
       count-overlaps))
