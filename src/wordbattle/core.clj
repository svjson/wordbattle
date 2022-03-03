(ns wordbattle.core
  (:gen-class)
  (:require [clojure.string :as string]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn blank-out [str mask]
  (->> (map vector str mask)
       (filter (fn [[_ rem]] (not rem)))
       (map first)
       (string/join)))

(defn count-wrong-pos [guess correct]
  (let [correct-bag (frequencies correct)]
    (reduce (fn [count [chr freq]]
              (+ count (min freq (get correct-bag chr 0))))
            0
            (frequencies guess))))

(defn evaluate-guess [guess correct-word]
  (let [correct-positions (map = guess correct-word)]

    {:word guess
     :right-pos (count (filter true? correct-positions))
     :wrong-pos (count-wrong-pos (blank-out guess correct-positions)
                                 (blank-out correct-word correct-positions))}))
