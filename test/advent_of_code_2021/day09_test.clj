(ns advent-of-code-2021.day09-test
  (:require [advent-of-code-2021.day09 :as day09]
            [clojure.java.io :as io]
            [clojure.test :refer [deftest is]]))

(def sample-input
  ["2199943210"
   "3987894921"
   "9856789892"
   "8767896789"
   "9899965678"])

(def input-file (io/resource "input09.txt"))

(deftest part1-test
  (is (= 15 (day09/part1 sample-input)))
  (with-open [r (io/reader input-file)]
    (is (= 554 (day09/part1 (line-seq r))))))

(deftest part2-test
  (is (= 1134 (day09/part2 sample-input)))
  (with-open [r (io/reader input-file)]
    (is (= 1017792 (day09/part2 (line-seq r))))))
