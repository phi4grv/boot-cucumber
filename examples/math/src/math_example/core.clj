(ns math-example.core)

(defn add-stuff [& numbers]
  (apply + numbers))

(defn multiply-stuff [& numbers]
  (apply * numbers))

(defn subtract-stuff [& numbers]
  (apply - numbers))