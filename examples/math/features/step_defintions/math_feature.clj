(require '[clojure.test :refer :all]
         '[math-example.core :refer :all])

(def result (atom nil))

(Before []
        (prn "Set up your fixtures here!"))

(After []
       (prn "Clean up your fixtures here!"))

(When #"^(\d+) and (\d+) are added$" [number1 number2]
      (reset! result (add-stuff (Long. number1) (Long. number2))))

(When #"(\d+) and (\d+) are subtracted from (\d+)$" [number1 number2 number3]
      (reset! result (subtract-stuff (Long. number3) (Long. number1) (Long. number2))))

(When #"^(\d+) and (\d+) are multiplied$" [number1 number2]
      (reset! result (multiply-stuff (Long. number1) (Long. number2))))

(Then #"^The result is (\d+)$" [number]
      (assert (= (Long. number) @result)))