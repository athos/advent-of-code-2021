(ns advent-of-code-2021.day11)

(defn parse-map [lines]
  (->> (for [[i line] (map-indexed vector lines)
             [j c] (map-indexed vector line)]
         [[i j] (- (int c) (int \0))])
       (into {})))

(defn neighbors [m [y x]]
  (for [dy [-1 0 1]
        dx [-1 0 1]
        :let [pos [(+ y dy) (+ x dx)]]
        :when (and (not (= dy dx 0)) (get m pos))]
    pos))

(defn octopuses-to-be-flashed [flashed m]
  (reduce-kv (fn [acc pos v]
               (cond-> acc
                 (and (not (flashed pos))
                      (> v 9))
                 (conj pos)))
             #{} m))

(defn affected-octopuses [m flashed to-be-flashed]
  (into []
        (comp (distinct)
              (mapcat (partial neighbors m))
              (remove flashed))
        to-be-flashed))

(defn step [m]
  (loop [m (update-vals m inc)
         flashed (octopuses-to-be-flashed #{} m)
         affected (affected-octopuses m flashed flashed)]
    (if (seq affected)
      (let [m' (reduce #(update %1 %2 inc) m affected)
            to-be-flashed (octopuses-to-be-flashed flashed m')
            flashed' (into flashed to-be-flashed)]
        (recur m' flashed' (affected-octopuses m' flashed' to-be-flashed)))
      {:m (reduce #(assoc %1 %2 0) m flashed)
       :flashed flashed})))

(defn part1 [n lines]
  (loop [n n, m (parse-map lines), total 0]
    (if (zero? n)
      total
      (let [{:keys [m flashed]} (step m)]
        (recur (dec n) m (+ total (count flashed)))))))

(defn part2 [lines]
  (loop [i 1, m (parse-map lines)]
    (let [{:keys [m flashed]} (step m)]
      (if (= (count m) (count flashed))
        i
        (recur (inc i) m)))))
