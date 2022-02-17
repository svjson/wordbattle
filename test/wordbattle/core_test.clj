(ns wordbattle.core-test
  (:require [clojure.test :refer :all]
            [wordbattle.core :refer :all]))

(deftest test-blank-out

  (testing "....."
    (is (= (blank-out "BLAST" [false false false false false])
           "BLAST")))

  (testing "TTTTT"
    (is (= (blank-out "BLAST" [true true true true true])
           "")))

  (testing ".T.T."
    (is (= (blank-out "BLAST" [false true false true false])
           "BAT"))))

(deftest test-evaluate-guess
  (testing "SPACE vs SPARK"
    (is (= (evaluate-guess "SPACE" "SPARK")
           {:right-pos 3
            :wrong-pos 0})))

  (testing "CUMIN vs CAROM"
    (is (= (evaluate-guess "CUMIN" "CAROM")
           {:right-pos 1
            :wrong-pos 1})))

  (testing "GHOST vs PAPER"
    (is (= (evaluate-guess "GHOST" "PAPER")
           {:right-pos 0
            :wrong-pos 0})))

  (testing "CUBIT vs CUBIT"
    (is (= (evaluate-guess "CUBIT" "CUBIT")
           {:right-pos 5
            :wrong-pos 0})))

  (testing "GLEAN vs ANGLE"
    (is (= (evaluate-guess "GLEAN" "ANGLE")
           {:right-pos 0
            :wrong-pos 5}))))
