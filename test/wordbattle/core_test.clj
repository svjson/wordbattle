(ns wordbattle.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [wordbattle.core :as core]))

(deftest test-blank-out
  (testing "....."
    (is (= (core/blank-out "BLAST" [false false false false false])
           "BLAST")))

  (testing "TTTTT"
    (is (= (core/blank-out "BLAST" [true true true true true])
           "")))

  (testing ".T.T."
    (is (= (core/blank-out "BLAST" [false true false true false])
           "BAT"))))

(deftest test-evaluate-guess
  (testing "SPACE vs SPARK"
    (is (= (core/evaluate-guess "SPACE" "SPARK")
           {:word "SPACE"
            :right-pos 3
            :wrong-pos 0})))

  (testing "CUMIN vs CAROM"
    (is (= (core/evaluate-guess "CUMIN" "CAROM")
           {:word "CUMIN"
            :right-pos 1
            :wrong-pos 1})))

  (testing "GHOST vs PAPER"
    (is (= (core/evaluate-guess "GHOST" "PAPER")
           {:word "GHOST"
            :right-pos 0
            :wrong-pos 0})))

  (testing "CUBIT vs CUBIT"
    (is (= (core/evaluate-guess "CUBIT" "CUBIT")
           {:word "CUBIT"
            :right-pos 5
            :wrong-pos 0})))

  (testing "GLEAN vs ANGLE"
    (is (= (core/evaluate-guess "GLEAN" "ANGLE")
           {:word "GLEAN"
            :right-pos 0
            :wrong-pos 5}))))
