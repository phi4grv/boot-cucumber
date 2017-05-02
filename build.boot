(set-env!  
  :resource-paths #{"src"}
  :source-paths #{"src"}
  :dependencies   '[[info.cukes/cucumber-clojure "1.2.5"]
                    [com.cemerick/pomegranate "0.3.1"]]
  :repositories [["maven" {:url "http://central.maven.org/maven2/"}]
                 ["clojars" {:url "https://clojars.org/repo/"}]])

(def +version+ "1.0.2")

(task-options!
  pom {:project     'vonrosen/boot-cucumber
       :version     +version+
       :description "Boot task to run cucumber tests."
       :url         "https://github.com/vonrosen/boot-cucumber"
       :scm         {:url "https://github.com/vonrosen/boot-cucumber"}
       :license     {"EPL" "http://www.eclipse.org/legal/epl-v10.html"}})

(deftask build []
  (comp
   (pom)
   (jar)
   (install)))

(deftask deploy []
  (comp
   (build)
   (push :repo "clojars" :gpg-sign (not (.endsWith +version+ "-SNAPSHOT")))))