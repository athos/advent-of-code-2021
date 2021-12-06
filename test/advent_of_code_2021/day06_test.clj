(ns advent-of-code-2021.day06-test
  (:require [advent-of-code-2021.day06 :as day06]
            [clojure.test :refer [deftest is]]
            [clojure.java.io :as io]))

(def sample-input
  (day06/parse-state "3,4,3,1,2"))

(def input-file (io/resource "input06.txt"))

(deftest problem1-test
  (is (= 5934 (day06/problem1 sample-input)))
  (is (= 359999 (day06/problem1 (day06/parse-state (slurp input-file))))))

(deftest problem2-test
  (is (= 26984457539 (day06/problem2 sample-input)))
  (is (= 1631647919273 (day06/problem2 (day06/parse-state (slurp input-file))))))
