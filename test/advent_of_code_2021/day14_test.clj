(ns advent-of-code-2021.day14-test
  (:require [advent-of-code-2021.day14 :as day14]
            [clojure.java.io :as io]
            [clojure.test :refer [deftest is]]))

(def sample-input
  ["NNCB"
   ""
   "CH -> B"
   "HH -> N"
   "CB -> H"
   "NH -> C"
   "HB -> C"
   "HC -> B"
   "HN -> C"
   "NN -> C"
   "BH -> H"
   "NC -> B"
   "NB -> B"
   "BN -> B"
   "BB -> N"
   "BC -> B"
   "CC -> N"
   "CN -> C"])

(def input-file (io/resource "input14.txt"))

(deftest part1-test
  (is (= 1588 (day14/part1 sample-input)))
  (with-open [r (io/reader input-file)]
    (is (= 2590 (day14/part1 (line-seq r))))))

(deftest part2-test
  (is (= 2188189693529 (day14/part2 sample-input)))
  (with-open [r (io/reader input-file)]
    (is (= 2875665202438 (day14/part2 (line-seq r))))))
