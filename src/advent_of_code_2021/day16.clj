(ns advent-of-code-2021.day16)

(def ^:private bool->bit {true 1 false 0})

(defn- expand-to-bits [cs]
  (for [c cs
        :let [n (Character/digit c 16)]
        b (map #(bool->bit (bit-test n %)) [3 2 1 0])]
    b))

(defn- decode-int* [bs]
  (reduce (fn [n b] (+ (* 2 n) b)) 0 bs))

(defn- decode-int [n bs]
  (let [[digits more] (split-at n bs)]
    [(decode-int* digits) more]))

(defn- decode-literal-packet [bs]
  (loop [[group more] (split-at 5 bs)
         acc []]
    (if (= (first group) 1)
      (recur (split-at 5 more) (into acc (rest group)))
      [(decode-int* (into acc (rest group)))
       more])))

(declare decode-packet)

(defn- decode-operator-packet [bs]
  (if (= (first bs) 0)
    (let [[nbits more] (decode-int 15 (rest bs))
          [bs more] (split-at nbits more)]
      (loop [bs bs, acc []]
        (if (seq bs)
          (let [[packet more] (decode-packet bs)]
            (recur more (conj acc packet)))
          [acc more])))
    (let [[n more] (decode-int 11 (rest bs))]
      (loop [n n, bs more, acc []]
        (if (zero? n)
          [acc bs]
          (let [[packet more] (decode-packet bs)]
            (recur (dec n) more (conj acc packet))))))))

(defn- decode-packet [bs]
  (let [[ver more] (decode-int 3 bs)
        [typ more] (decode-int 3 more)
        packet {:ver ver, :type typ}]
    (if (= typ 4)
      (let [[value more] (decode-literal-packet more)]
        [(assoc packet :value value) more])
      (let [[packets more] (decode-operator-packet more)]
        [(assoc packet :packets packets) more]))))

(defn- decode [cs]
  (first (decode-packet (expand-to-bits cs))))

(defn part1 [input]
  (letfn [(rec [packet]
            (cond-> (:ver packet)
              (:packets packet)
              (+ (apply + (map rec (:packets packet))))))]
    (rec (decode input))))

(defn- eval-packet [{:keys [type value packets]}]
  (or value
      (apply ({0 + 1 * 2 min 3 max 5 (comp bool->bit >)
               6 (comp bool->bit <) 7 (comp bool->bit =)}
              type)
             (map eval-packet packets))))

(defn part2 [input]
  (eval-packet (decode input)))
