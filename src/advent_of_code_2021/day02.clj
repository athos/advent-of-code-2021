(ns advent-of-code-2021.day02)

(defn parse-command [line]
  (let [[_ op arg] (re-matches #"(\w+) (\d+)" line)]
    [(keyword op) (parse-long arg)]))

(defn step1 [state [op by]]
  (case op
    :forward (update state :x + by)
    :down (update state :y + by)
    :up (update state :y - by)))

(defn- run [f state0 commands]
  (let [{:keys [x y]} (reduce f state0 commands)]
    (* x y)))

(defn problem1 [commands]
  (run step1 {:x 0 :y 0} commands))

(defn step2 [{:keys [aim] :as state} [op by]]
  (case op
    :forward (-> state
                 (update :x + by)
                 (update :y + (* aim by)))
    :down (update state :aim + by)
    :up (update state :aim - by)))

(defn problem2 [commands]
  (run step2 {:x 0 :y 0 :aim 0} commands))
