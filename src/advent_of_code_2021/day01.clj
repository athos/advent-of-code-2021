(ns advent-of-code-2021.day01)

(defn problem1 [nums]
  (->> nums
       (partition 2 1)
       (filter (fn [[m n]] (< m n)))
       count))

(defn problem2 [nums]
  (->> nums
       (partition 3 1)
       (map #(apply + %))
       problem1))
