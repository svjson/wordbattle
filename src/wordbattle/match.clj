(ns wordbattle.match
  (:require [wordbattle.core :as word]))

(def current-match (atom {}))

(defn begin-match! [match]
  (reset! match {:word "SPACE"
                 :attempts []
                 :status :ongoing}))

(defn make-attempt! [match guess]
  (let [result (word/evaluate-guess guess (:word @match))]
    (swap! match assoc :attempts (conj (:attempts @match) result))
    (when (= (:right-pos result) 5)
      (swap! match assoc :status :won))))

(comment
(begin-match! current-match)
(conj (:attempts @current-match) (word/evaluate-guess "SPACE" (:word @current-match)))
(swap! current-match assoc :attempts (conj (:attempts @current-match) (word/evaluate-guess "SPACE" (:word @current-match))))
(make-attempt! current-match "TRACK")
(make-attempt! current-match "SPACE")
)

