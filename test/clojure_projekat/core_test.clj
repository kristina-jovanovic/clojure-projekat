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

(facts (colors-match? :white :black) => true)
(facts (seasons-match? white-t-shirt black-pants) => true)
(facts (combination-valid? white-t-shirt black-pants) => true)
(facts (combination-of-more-pieces? [white-t-shirt black-pants white-sneakers]) => true)

