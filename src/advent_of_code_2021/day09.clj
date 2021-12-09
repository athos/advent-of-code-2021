(ns advent-of-code-2021.day09
  (:import clojure.lang.PersistentQueue))

(defn parse-hmap [lines]
  (mapv (fn [line] (mapv #(- (int %) (int \0)) line)) lines))

(defn neighbors [hmap [y x]]
  (keep (fn [[dy dx]]
          (let [pos [(+ y dy) (+ x dx)]]
            (when (get-in hmap pos)
              pos)))
        [[0 1] [1 0] [0 -1] [-1 0]]))

(defn collect-lowest-points [hmap]
  (for [y (range (count hmap))
        x (range (count (first hmap)))
        :let [pos [y x], v (get-in hmap pos)]
        :when (every? #(< v (get-in hmap %)) (neighbors hmap pos))]
    pos))

(defn part1 [lines]
  (let [hmap (parse-hmap lines)]
    (->> (collect-lowest-points hmap)
         (map #(inc (get-in hmap %)))
         (apply +))))

(defn expand-basin [hmap lowest-point]
  (loop [queue (conj PersistentQueue/EMPTY lowest-point)
         visited #{}]
    (if-let [pos (peek queue)]
      (recur (into (pop queue)
                   (remove #(or (visited %) (= (get-in hmap %) 9)))
                   (neighbors hmap pos))
             (conj visited pos))
      visited)))

(defn part2 [lines]
  (let [hmap (parse-hmap lines)]
    (->> (collect-lowest-points hmap)
         (map #(count (expand-basin hmap %)))
         (sort-by -)
         (take 3)
         (apply *))))
