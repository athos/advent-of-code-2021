(ns advent-of-code-2021.day12
  (:require [clojure.string :as str]))

(defn parse-graph [lines]
  (->> lines
       (map #(str/split % #"-"))
       (reduce (fn [acc [x y]]
                 (-> acc
                     (update x (fnil conj #{}) y)
                     (update y (fnil conj #{}) x)))
               {})))

(defn small? [^String node]
  (Character/isLowerCase (Character/codePointAt node 0)))

(defn step1 [{:keys [graph loc path]}]
  (for [node (graph loc)]
    {:graph (cond-> graph (small? loc) (dissoc loc))
     :loc node
     :path (conj path loc)}))

(defn run [step initial-state]
  (loop [states [initial-state], paths #{}]
    (if (seq states)
      (let [{finished true, continued false} (->> (step (peek states))
                                                  (group-by #(= (:loc %) "end")))]
        (recur (into (pop states) continued)
               (into paths (map :path) finished)))
      (count paths))))

(defn part1 [lines]
  (run step1 {:graph (parse-graph lines) :loc "start" :path []}))

(defn step2 [{:keys [graph loc path flag]}]
  (for [node (graph loc)
        flag' (cond (= "start" loc) [false]
                    flag [true]
                    :else [false true])]
    {:graph (cond-> graph (and (small? loc) (= flag flag')) (dissoc loc))
     :loc node
     :path (conj path loc)
     :flag flag'}))

(defn part2 [lines]
  (run step2 {:graph (parse-graph lines) :loc "start" :path [] :flag false}))

(comment
  (require '[clojure.java.io :as io])
  (with-open [r (io/reader (io/resource "input12.txt"))]
    (time (part2 (line-seq r))))
  )
