(ns advent-of-code-2021.day03)

(defn- bits->num [bs]
  (Long/parseLong (apply str bs) 2))

(defn- make-selector [f tie-breaker]
  (fn [vs]
    (let [freqs (frequencies vs)]
      (if (apply = (vals freqs))
        tie-breaker
        (key (apply f val freqs))))))

(def ^:private most-common-bit (make-selector max-key \1))
(def ^:private least-common-bit (make-selector min-key \0))

(defn problem1 [lines]
  (* (->> (apply map vector lines)
          (map most-common-bit)
          bits->num)
     (->> (apply map vector lines)
          (map least-common-bit)
          bits->num)))

(defn- choose-by [f lines]
  (loop [lines lines, ret []]
    (if (and (first lines) (second lines))
      (let [b (f (map first lines))]
        (recur (keep #(when (= b (first %)) (rest %)) lines)
               (conj ret b)))
      (into ret (first lines)))))

(defn problem2 [lines]
  (* (bits->num (choose-by most-common-bit lines))
     (bits->num (choose-by least-common-bit lines))))
