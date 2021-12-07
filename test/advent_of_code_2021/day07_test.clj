(ns advent-of-code-2021.day07-test
  (:require [advent-of-code-2021.day07 :as day07]
            [clojure.java.io :as io]
            [clojure.test :refer [deftest is]]))

(def sample-input "16,1,2,0,4,2,7,1,2,14")

(def input-file (io/resource "input07.txt"))

(deftest part1-test
  (is (= 37 (day07/part1 sample-input)))
  (is (= 352331 (day07/part1 (slurp input-file)))))

(deftest part2-test
  (is (= 168 (day07/part2 sample-input)))
  (is (= 99266250 (day07/part2 (slurp input-file)))))
