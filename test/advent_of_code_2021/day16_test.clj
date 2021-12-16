(ns advent-of-code-2021.day16-test
  (:require [advent-of-code-2021.day16 :as day16]
            [clojure.java.io :as io]
            [clojure.test :refer [deftest is are]]))

(def input-file (io/resource "input16.txt"))

(deftest part1-test
  (are [input expected] (= expected (day16/part1 input))
    "8A004A801A8002F478" 16
    "620080001611562C8802118E34" 12
    "C0015000016115A2E0802F182340" 23
    "A0016C880162017C3686B18A3D4780" 31)
  (is (= 947 (day16/part1 (slurp input-file)))))

(deftest part2-test
  (are [input expected] (= expected (day16/part2 input))
    "C200B40A82" 3
    "04005AC33890" 54
    "880086C3E88112" 7
    "CE00C43D881120" 9
    "D8005AC2A8F0" 1
    "F600BC2D8F" 0
    "9C005AC2F8F0" 0
    "9C0141080250320F1802104A08" 1))
