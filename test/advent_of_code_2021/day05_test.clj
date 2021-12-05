(ns advent-of-code-2021.day05-test
  (:require [advent-of-code-2021.day05 :as day05]
            [clojure.java.io :as io]
            [clojure.test :refer [deftest is]]))

(def sample-input
  ["0,9 -> 5,9"
   "8,0 -> 0,8"
   "9,4 -> 3,4"
   "2,2 -> 2,1"
   "7,0 -> 7,4"
   "6,4 -> 2,0"
   "0,9 -> 2,9"
   "3,4 -> 1,4"
   "0,0 -> 8,8"
   "5,5 -> 8,2"])

(def input-file (io/resource "input05.txt"))

(deftest problem1-test
  (is (= 5 (day05/problem1 sample-input)))
  (with-open [r (io/reader input-file)]
    (is (= 6548 (day05/problem1 (line-seq r))))))

(deftest problem2-test
  (is (= 12 (day05/problem2 sample-input)))
  (with-open [r (io/reader input-file)]
    (is (= 19663 (day05/problem2 (line-seq r))))))
