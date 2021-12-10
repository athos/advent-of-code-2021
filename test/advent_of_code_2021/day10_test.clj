(ns advent-of-code-2021.day10-test
  (:require [advent-of-code-2021.day10 :as day10]
            [clojure.java.io :as io]
            [clojure.test :refer [deftest is]]))

(def sample-input
  ["[({(<(())[]>[[{[]{<()<>>"
   "[(()[<>])]({[<{<<[]>>("
   "{([(<{}[<>[]}>{[]{[(<()>"
   "(((({<>}<{<{<>}{[]{[]{}"
   "[[<[([]))<([[{}[[()]]]"
   "[{[{({}]{}}([{[{{{}}([]"
   "{<[[]]>}<{[{[{[]{()[[[]"
   "[<(<(<(<{}))><([]([]()"
   "<{([([[(<>()){}]>(<<{{"
   "<{([{{}}[<[[[<>{}]]]>[]]"])

(def input-file (io/resource "input10.txt"))

(deftest part1-test
  (is (= 26397 (day10/part1 sample-input)))
  (with-open [r (io/reader input-file)]
    (is (= 399153 (day10/part1 (line-seq r))))))

(deftest part2-test
  (is (= 288957 (day10/part2 sample-input)))
  (with-open [r (io/reader input-file)]
    (is (= 2995077699 (day10/part2 (line-seq r))))))
