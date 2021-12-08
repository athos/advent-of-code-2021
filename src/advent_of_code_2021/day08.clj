(ns advent-of-code-2021.day08
  (:require [clojure.math.combinatorics :as comb]
            [clojure.set :as set]
            [clojure.string :as str]))

(def ^:private segs (vec "abcdefg"))

(def ^:private num->segs
  {0 #{\a \b \c \e \f \g}
   1 #{\c \f}
   2 #{\a \c \d \e \g}
   3 #{\a \c \d \f \g}
   4 #{\b \c \d \f}
   5 #{\a \b \d \f \g}
   6 #{\a \b \d \e \f \g}
   7 #{\a \c \f}
   8 #{\a \b \c \d \e \f \g}
   9 #{\a \b \c \d \f \g}})

(def ^:private segs->num
  (set/map-invert num->segs))

(defn- parse-line [line]
  (let [[pats outs] (str/split line #" \| ")]
    {:pats (str/split pats #" ")
     :outs (str/split outs #" ")}))

(defn- step [segs->num assign pat]
  (let [segs (into #{} (map assign) pat)]
    (when (contains? segs->num segs)
      (dissoc segs->num segs))))

(defn- consistent? [pats assign]
  (->> pats
       (reduce (fn [segs->num pat]
                 (or (step segs->num assign pat)
                     (reduced nil)))
               segs->num)
       boolean))

(defn- resolve-assignment [pats]
  (->> (comb/permutations segs)
       (map #(zipmap segs %))
       (filter (partial consistent? pats))
       first))

(defn- process-line [line]
  (let [{:keys [pats outs]} (parse-line line)
        assign (resolve-assignment pats)]
    (map #(segs->num (set (map assign %))) outs)))

(defn part1 [lines]
  (->> lines
       (mapcat process-line)
       (filter #{1 4 7 8})
       frequencies
       vals
       (apply +)))

(defn part2 [lines]
  (->> lines
       (map process-line)
       (reduce (fn [sum [d1 d2 d3 d4]]
                 (+ sum (* 1000 d1) (* 100 d2) (* 10 d3) d4))
               0)))
