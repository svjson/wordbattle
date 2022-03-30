(ns wordbattle.match-test
  (:require [clojure.test :refer [deftest testing is]]
            [wordbattle.match :as match]))

(deftest test-begin-match
  (let [match (atom {})]
    (match/begin-match! match "SPACE")
    (is (= "SPACE" (:word @match)))
    (is (= [] (:attempts @match)))
    (is (= :ongoing (:status @match)))))

(deftest test-make-attempt!
  (testing "false first guess"
    (let [match (atom {})]
      (match/begin-match! match "SPACE")
      (match/make-attempt! match "TRACK")
      (is (= (:attempts @match)
             [{:right-pos 2
               :wrong-pos 0
               :word "TRACK"}]))
      (is (= :ongoing (:status @match)))))
  (testing "correct guess"
    (let [match (atom {})]
      (match/begin-match! match "SPACE")
      (match/make-attempt! match "SPACE")
      (is (= (:attempts @match)
             [{:right-pos 5
               :wrong-pos 0
               :word "SPACE"}]))
      (is (= :won (:status @match)))))
  (testing "correct second guess"
    (let [match (atom {})]
      (match/begin-match! match "SPACE")
      (match/make-attempt! match "TRACK")
      (match/make-attempt! match "SPACE")
      (is (= (:attempts @match)
             [{:right-pos 2 :wrong-pos 0 :word "TRACK"}
              {:right-pos 5 :wrong-pos 0 :word "SPACE"}]))
      (is (= :won (:status @match))))))
