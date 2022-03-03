(ns wordbattle.match-test
  (:require [clojure.test :refer :all]
            [wordbattle.match :refer :all]))

(deftest test-begin-match
  (let [match (atom {})]
    (begin-match! match)
    (is (= 5 (count (:word @match))))
    (is (= [] (:attempts @match)))
    (is (= :ongoing (:status @match)))))

(deftest test-make-attempt!
  (testing "false guess"
    (let [match (atom {})]
      (begin-match! match)
      (make-attempt! match "TRACK")
      (is (= 1 (count (:attempts @match))))
      (is (= :ongoing (:status @match)))))
  (testing "correct guess"
    (let [match (atom {})]
      (begin-match! match)
      (make-attempt! match "SPACE")
      (is (= 1 (count (:attempts @match))))
      (is (= :won (:status @match))))))
