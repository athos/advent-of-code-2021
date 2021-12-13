(ns advent-of-code-2021.day13-test
  (:require [advent-of-code-2021.day13 :as day13]
            [clojure.java.io :as io]
            [clojure.test :refer [deftest is]]
            [clojure.string :as str]))

(def sample-input
  ["6,10"
   "0,14"
   "9,10"
   "0,3"
   "10,4"
   "4,11"
   "6,0"
   "6,12"
   "4,1"
   "0,13"
   "10,12"
   "3,4"
   "3,0"
   "8,4"
   "1,10"
   "2,14"
   "8,10"
   "9,0"
   ""
   "fold along y=7"
   "fold along x=5"])

(def input-file (io/resource "input13.txt"))

(deftest part1-test
  (is (= 17 (day13/part1 sample-input)))
  (with-open [r (io/reader input-file)]
    (is (= 790 (day13/part1 (line-seq r))))))

(deftest part2-test
  (is (= (str/join \newline
                   ["#####"
                    "#...#"
                    "#...#"
                    "#...#"
                    "#####"
                    ""])
         (with-out-str
           (day13/part2 sample-input))))
  (is (= (str/join \newline
                   ["###...##..#..#.####.###..####...##..##."
                    "#..#.#..#.#..#....#.#..#.#.......#.#..#"
                    "#..#.#....####...#..###..###.....#.#..."
                    "###..#.##.#..#..#...#..#.#.......#.#..."
                    "#....#..#.#..#.#....#..#.#....#..#.#..#"
                    "#.....###.#..#.####.###..#.....##...##."
                    ""])
         (with-open [r (io/reader input-file)]
           (with-out-str
             (day13/part2 (line-seq r)))))))
