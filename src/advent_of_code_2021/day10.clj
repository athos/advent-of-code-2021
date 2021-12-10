(ns advent-of-code-2021.day10)

(def ^:private symbols
  {\( \), \[ \], \{ \}, \< \>})

(defn- run [f line]
  (loop [[c & cs] line, stack []]
    (cond (not c) (f c stack)
          (contains? symbols c) (recur cs (conj stack (symbols c)))
          (and (seq stack) (= c (peek stack))) (recur cs (pop stack))
          :else (f c stack))))

(defn- find-first-illegal-char [line]
  (run (fn [c _] c) line))

(defn part1 [lines]
  (->> lines
       (keep find-first-illegal-char)
       (map (zipmap ")]}>" [3 57 1197 25137]))
       (apply +)))

(defn- complete-closing-chars [line]
  (run (fn [c stack] (when-not c (rseq stack))) line))

(defn- calc-score [cs]
  (reduce #(+ (* 5 %1) ((zipmap ")]}>" [1 2 3 4]) %2)) 0 cs))

(defn part2 [lines]
  (let [scores (->> lines
                    (keep complete-closing-chars)
                    (map calc-score)
                    sort)]
    (nth scores (quot (count scores) 2))))
