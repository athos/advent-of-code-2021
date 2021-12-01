(ns build
  (:refer-clojure :exclude [test])
  (:require [org.corfield.build :as bb]))

(defn test [opts]
  (bb/run-tests opts))
