(ns clojure-projekat.core-test
  (:require [clojure.test :refer :all]
            [clojure-projekat.core :refer :all]
            [midje.sweet :refer [facts =>]]))

;(deftest a-test
;  (testing "FIXME, I fail."
;    (is (= 0 1))))

;(facts "test" true => false)
;
;(facts (recommend-what-to-wear) => nil)
;(facts (recommend-what-to-wear) => string?)
;
;(facts (distinct (take 5 (repeatedly recommend-what-to-wear))) => true)

(facts (boje-se-slazu? :bela :crna) => true)
(facts (sezone-iste? majica-bela pantalone-crne) => true)
(facts (kombinacija-validna? majica-bela pantalone-crne) => true)
(facts (kombinacija-vise-komada? [majica-bela pantalone-crne patike-bele]) => true)

