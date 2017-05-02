(ns math-example.test
  (:require [clojure.test :refer :all]
            [math-example.core :as math]))

(deftest test-adding
  (is (= 4 (math/add-stuff 1 1 1 1))))

(deftest test-multiplying
  (is (= 10 (math/multiply-stuff 2 5 1 ))))

(deftest test-subtracting
  (is (= 2 (math/subtract-stuff 4 1 1))))