(ns advent-of-code-2021.day15)

(defn- parse-grid [lines]
  (->> (for [[i line] (map-indexed vector lines)
             [j c] (map-indexed vector line)]
         [[i j] (- (int c) (int \0))])
       (into {})))

(defn- val-at [{:keys [grid size repeat]} [y x]]
  (let [n (* size repeat)]
    (when (and (< -1 y n) (< -1 x n))
      (let [yq (quot y size), yr (rem y size)
            xq (quot x size), xr (rem x size)
            v (+ (get grid [yr xr]) yq xq)]
        (if (< v 10)
          v
          (+ (quot v 10) (rem v 10)))))))

(defn- neighbors [n [y x]]
  (keep (fn [[dy dx]]
          (let [y' (+ y dy), x' (+ x dx)]
            (when (and (<= 0 y' (dec n)) (<= 0 x' (dec n)))
              [y' x'])))
        [[0 1] [1 0] [0 -1] [-1 0]]))

(defn- enqueue [queue priority x]
  (update queue priority (fnil conj #{}) x))

(defn- dequeue [queue]
  (let [[k xs] (->> queue (filter (comp seq val)) first)
        x (first xs)
        queue' (update queue k disj x)]
    [[k x]
     (->> (subseq queue' >= k)
          (take-while (comp empty? val))
          (map key)
          (apply dissoc queue'))]))

(defn- lowest-total-risk [{:keys [size repeat] :as grid}]
  (let [n (* size repeat)
        goal [(dec n) (dec n)]]
    (loop [queue (sorted-map 0 #{[0 0]}), risks {[0 0] 0}, resolved #{}]
      (if (resolved goal)
        (risks goal)
        (let [[[k pos] queue'] (dequeue queue)
              prs (for [p (neighbors n pos)
                        :when (not (resolved p))
                        :let [r (risks p), r' (+ k (val-at grid p))]
                        :when (or (nil? r) (< r' r))]
                    [p r'])]
          (recur (reduce (fn [q [p r]] (enqueue q r p)) queue' prs)
                 (reduce (fn [rs [p r]] (assoc rs p r)) risks prs)
                 (conj resolved pos)))))))

(defn part1 [n lines]
  (lowest-total-risk {:grid (parse-grid lines) :size n :repeat 1}))

(defn part2 [n lines]
  (lowest-total-risk {:grid (parse-grid lines) :size n :repeat 5}))
