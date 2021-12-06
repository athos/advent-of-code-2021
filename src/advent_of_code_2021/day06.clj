(ns advent-of-code-2021.day06
  (:require [clojure.edn :as edn]))

(defn parse-state [s]
  (frequencies (edn/read-string (str \[ s \]))))

(defn step [{z 0 :or {z 0} :as state}]
  (-> state
      (dissoc 0)
      (update-keys #(max 0 (dec %)))
      (update 6 (fnil + 0) z)
      (update 8 (fnil + 0) z)))

(defn run [n state]
  (->> (reduce (fn [state _] (step state)) state (range n))
       vals
       (apply +)))

(defn problem1 [state]
  (run 80 state))

(defn problem2 [state]
  (run 256 state))
