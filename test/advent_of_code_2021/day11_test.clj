(ns advent-of-code-2021.day11-test
  (:require [advent-of-code-2021.day11 :as day11]
            [clojure.java.io :as io]
            [clojure.test :refer [deftest is]]))

(def sample-input
  ["5483143223"
   "2745854711"
   "5264556173"
   "6141336146"
   "6357385478"
   "4167524645"
   "2176841721"
   "6882881134"
   "4846848554"
   "5283751526"])

(def input-file (io/resource "input11.txt"))

(deftest part1-test
  (is (= 1656 (day11/part1 100 sample-input)))
  (with-open [r (io/reader input-file)]
    (is (= 1732 (day11/part1 100 (line-seq r))))))

(deftest part2-test
  (is (= 195 (day11/part2 sample-input)))
  (with-open [r (io/reader input-file)]
    (is (= 290 (day11/part2 (line-seq r))))))
