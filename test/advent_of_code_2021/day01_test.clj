(ns advent-of-code-2021.day01-test
  (:require [advent-of-code-2021.day01 :as day01]
            [clojure.java.io :as io]
            [clojure.test :refer [deftest is]]))

(def sample-input
  [199 200 208 210 200 207 240 269 260 263])

(def input-file (io/resource "input01.txt"))

(deftest problem1-test
  (is (= 7 (day01/problem1 sample-input)))
  (with-open [r (io/reader input-file)]
    (is (= 1462 (day01/problem1 (map parse-long (line-seq r)))))))

(deftest problem2-test
  (is (= 5 (day01/problem2 sample-input)))
  (with-open [r (io/reader input-file)]
    (is (= 1497 (day01/problem2 (map parse-long (line-seq r)))))))
