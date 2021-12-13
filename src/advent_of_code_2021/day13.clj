(ns advent-of-code-2021.day13)

(defn parse-lines [lines]
  (reduce (fn [acc line]
            (condp re-matches line
              #"(\d+),(\d+)"
              :>> (fn [[_ & coord]]
                    (let [[x y] (map parse-long coord)
                          p (long-array [x y])]
                      (-> acc
                          (update-in [:points :y y] (fnil conj []) p)
                          (update-in [:points :x x] (fnil conj []) p))))
              #"fold along ([xy])=(\d+)"
              :>> (fn [[_ axis pos]]
                    (update acc :instrs conj
                            {:axis (keyword axis), :pos (parse-long pos)}))
              acc))
          {:points {:y (sorted-map), :x (sorted-map)}, :instrs []}
          lines))

(defn val-for-axis [^longs p axis]
  (aget p (case axis :x 0 :y 1)))

(defn set-val-for-axis! [^longs p axis ^long v]
  (aset p (case axis :x 0 :y 1) v))

(defn val-after-folding [val pos]
  (- val (* 2 (- val pos))))

(defn print-points [points]
  (doseq [y (range (inc (key (first (rseq (:y points))))))
          :let [xs (into #{} (map #(val-for-axis % :x))
                         (get-in points [:y y]))]]
    (doseq [x (range (inc (key (first (rseq (:x points))))))]
      (print (if (contains? xs x) \# \.)))
    (newline)))

(defn step [points {:keys [axis pos]}]
  (let [ps-for-axis (get points axis)
        targets (subseq ps-for-axis > pos)]
    (assoc points axis
           (reduce (fn [m [v ps]]
                     (let [v' (val-after-folding v pos)]
                       (run! #(set-val-for-axis! % axis v') ps)
                       (update m v' (fnil into []) ps)))
                   (apply dissoc ps-for-axis (keys targets))
                   targets))))

(defn count-unique-points [points]
  (->> (for [[y ps] (:y points)
             x (into #{} (map #(val-for-axis % :x)) ps)]
         [y x])
       count))

(defn part1 [lines]
  (let [{:keys [points instrs]} (parse-lines lines)]
    (count-unique-points (step points (first instrs)))))

(defn part2 [lines]
  (let [{:keys [points instrs]} (parse-lines lines)]
    (->> instrs
         (reduce step points)
         print-points)))
