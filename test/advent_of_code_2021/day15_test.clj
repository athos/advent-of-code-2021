(ns advent-of-code-2021.day15-test
  (:require [advent-of-code-2021.day15 :as day15]
            [clojure.java.io :as io]
            [clojure.test :refer [deftest is]]))

(def sample-input
  ["1163751742"
   "1381373672"
   "2136511328"
   "3694931569"
   "7463417111"
   "1319128137"
   "1359912421"
   "3125421639"
   "1293138521"
   "2311944581"])

(def input-file (io/resource "input15.txt"))

(deftest part1-test
  (is (= 40 (day15/part1 10 sample-input)))
  (with-open [r (io/reader input-file)]
    (is (= 656 (day15/part1 100 (line-seq r))))))

(deftest part2-test
  (is (= 315 (day15/part2 10 sample-input)))
  (with-open [r (io/reader input-file)]
    (is (= 2979 (day15/part2 100 (line-seq r))))))
