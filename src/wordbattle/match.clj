(ns wordbattle.match
  (:require [wordbattle.core :as word]))

(def current-match (atom {}))

(defn begin-match!
  ([match] (begin-match! match "SPACE"))
  ([match word]
   (reset! match {:word word
                  :attempts []
                  :status :ongoing})))

(defn make-attempt! [match guess]
  (let [result (word/evaluate-guess guess (:word @match))
        next-status (if (= (:right-pos result) 5) :won :ongoing)]
    (swap! match assoc :attempts (conj (:attempts @match) result))
    (swap! match assoc :status next-status)))

(comment
  (begin-match! current-match)
  (begin-match! current-match "FLUSH")
  (conj (:attempts @current-match) (word/evaluate-guess "SPACE" (:word @current-match)))
  (swap! current-match assoc :attempts (conj (:attempts @current-match) (word/evaluate-guess "SPACE" (:word @current-match))))

  (make-attempt! current-match "TRACK")

  (make-attempt! current-match "SPACE")
  )

