(ns advent-of-code-2021.day02-test
  (:require [clojure.test :refer [deftest is]]
            [advent-of-code-2021.day02 :as day02]
            [clojure.java.io :as io]))

(def sample-input
  (->> ["forward 5"
        "down 5"
        "forward 8"
        "up 3"
        "down 8"
        "forward 2"]
       (mapv day02/parse-command)))

(def input-file (io/resource "input02.txt"))

(deftest problem1-test
  (is (= 150 (day02/problem1 sample-input)))
  (with-open [r (io/reader input-file)]
    (is (= 1690020 (day02/problem1 (map day02/parse-command (line-seq r)))))))

(deftest problem2-test
  (is (= 900 (day02/problem2 sample-input)))
  (with-open [r (io/reader input-file)]
    (is (= 1408487760 (day02/problem2 (map day02/parse-command (line-seq r)))))))
