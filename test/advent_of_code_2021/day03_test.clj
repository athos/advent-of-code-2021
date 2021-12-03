(ns advent-of-code-2021.day03-test
  (:require [advent-of-code-2021.day03 :as day03]
            [clojure.java.io :as io]
            [clojure.test :refer [deftest is]]))

(def sample-input
  ["00100"
   "11110"
   "10110"
   "10111"
   "10101"
   "01111"
   "00111"
   "11100"
   "10000"
   "11001"
   "00010"
   "01010"])

(def input-file (io/resource "input03.txt"))

(deftest problem1-test
  (is (= 198 (day03/problem1 sample-input)))
  (with-open [r (io/reader input-file)]
    (is (= 2967914 (day03/problem1 (line-seq r))))))

(deftest problem2-test
  (is (= 230 (day03/problem2 sample-input)))
  (with-open [r (io/reader input-file)]
    (is (= 7041258 (day03/problem2 (line-seq r))))))
