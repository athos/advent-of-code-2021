(ns advent-of-code-2021.day14)

(defn- parse-input [lines]
  {:template (first lines)
   :rules (reduce (fn [m line]
                    (let [[_ pre post] (re-matches #"(\w+) -> (\w)" line)]
                      (assoc m (vec pre) (first post))))
                  {} (nnext lines))})

(defn- init-state [template]
  (->> (cons nil template)
       (partition 2 1 [nil])
       (map vec)
       frequencies))

(defn- step [rules freqs]
  (reduce-kv (fn [freqs [e1 e2 :as pre] n]
               (if-let [post (get rules pre)]
                 (-> freqs
                     (update pre - n)
                     (update [e1 post] (fnil + 0) n)
                     (update [post e2] (fnil + 0) n))
                 freqs))
             freqs freqs))

(defn- elem-stats [freqs]
  (-> (reduce-kv (fn [m [e1 e2] n]
                   (-> m
                       (cond-> e1 (update e1 (fnil + 0) n))
                       (cond-> e2 (update e2 (fnil + 0) n))))
                 {} freqs)
      (update-vals #(/ % 2))))

(defn- run [n lines]
  (let [{:keys [rules template]} (parse-input lines)
        vs (->> (init-state template)
                (iterate (partial step rules))
                (#(nth % n))
                elem-stats
                vals)]
    (- (apply max vs) (apply min vs))))

(defn part1 [lines]
  (run 10 lines))

(defn part2 [lines]
  (run 40 lines))
