(ns advent-of-code-2021.day12-test
  (:require [advent-of-code-2021.day12 :as day12]
            [clojure.java.io :as io]
            [clojure.test :refer [deftest is]]))

(def sample-input
  ["start-A"
   "start-b"
   "A-c"
   "A-b"
   "b-d"
   "A-end"
   "b-end"])

(def input-file (io/resource "input12.txt"))

(deftest part1-test
  (is (= 10 (day12/part1 sample-input)))
  (with-open [r (io/reader input-file)]
    (is (= 4754 (day12/part1 (line-seq r))))))

(deftest part2-test
  (is (= 36 (day12/part2 sample-input)))
  (with-open [r (io/reader input-file)]
    (is (= 143562 (day12/part2 (line-seq r))))))
