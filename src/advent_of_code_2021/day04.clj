(ns advent-of-code-2021.day04
  (:require [clojure.string :as str]))

(defn- parse-numbers
  ([line] (parse-numbers #"," line))
  ([delim line]
   (->> (str/split line delim)
        (keep #(some-> % parse-long)))))

(defn- parse-board [lines]
  (->> (for [[i line] (map-indexed vector lines)
             [j n] (map-indexed vector (parse-numbers #"\s+" line))]
         [n [i j]])
       (into {})))

(defn- parse-input [lines]
  {:nums (parse-numbers (first lines))
   :boards (->> (nthnext lines 2)
                (partition-by str/blank?)
                (partition-all 2)
                (into (sorted-map)
                      (map-indexed #(vector %1 (parse-board (first %2))))))})

(defn- mark-num [num boards states]
  (reduce-kv (fn [states k _]
               (if-let [[i j] (get-in boards [k num])]
                 (-> states
                     (update-in [k :rows i] (fnil conj []) j)
                     (update-in [k :cols j] (fnil conj []) i))
                 states))
             states
             states))

(defn- bingo? [size {:keys [rows cols]}]
  (or (some (fn [[_ v]] (= (count v) size)) rows)
      (some (fn [[_ v]] (= (count v) size)) cols)))

(defn- run1 [size boards nums]
  (reduce (fn [states num]
            (let [states' (mark-num num boards states)]
              (if-let [i (->> states'
                              (keep (fn [[i state]]
                                      (when (bingo? size state) i)))
                              first)]
                (reduced {:board (boards i)
                          :state (states' i)
                          :num num})
                states')))
          (into (sorted-map)
                (map (fn [[i _]] [i {}]))
                boards)
          nums))

(defn- calc-result [{:keys [board state num]}]
  (let [marked (->> (for [[row cols] (:rows state)
                          col cols]
                      [row col])
                    (into #{}))]
    (->> board
         (keep (fn [[k v]] (when-not (marked v) k)))
         (apply +)
         (* num))))

(defn problem1 [lines]
  (let [{:keys [boards nums]} (parse-input lines)]
    (calc-result (run1 5 boards nums))))

(defn- run2 [size boards nums]
  (loop [boards boards
         states (into boards (map (fn [[k _]] [k {}])) boards)
         [num & nums] nums]
    (let [states (mark-num num boards states)
          indices (keep (fn [[i state]]
                          (when (bingo? size state)
                            i))
                        states)
          boards' (apply dissoc boards indices)
          states' (apply dissoc states indices)]
      (if (seq boards')
        (recur boards' states' nums)
        {:board (val (first boards))
         :state (val (first states))
         :num num}))))

(defn problem2 [lines]
  (let [{:keys [boards nums]} (parse-input lines)]
    (calc-result (run2 5 boards nums))))
