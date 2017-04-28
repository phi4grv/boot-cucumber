(set-env!
  :resource-paths #{"src"}
  :source-paths #{"test"}
  :dependencies   '[[info.cukes/cucumber-clojure "1.2.5"]]
  :repositories [["maven" {:url "http://central.maven.org/maven2/"}]
                 ["clojars" {:url "https://clojars.org/repo/"}]])

(def +version+ "1.0.0-SNAPSHOT")

(task-options!
  pom {:project     'vonrosen/boot-cucumber
       :version     +version+
       :description "Boot task to run cucumber tests."
       :url         "https://github.com/vonrosen/boot-cucumber"
       :scm         {:url "https://github.com/vonrosen/boot-cucumber"}
       :license     {"EPL" "http://www.eclipse.org/legal/epl-v10.html"}})

(ns-unmap *ns* 'test)

(deftask build []
  (comp
   (pom)
   (jar)
   (install)))

(deftask deploy []
  (comp
   (build)
   (push :repo "clojars" :gpg-sign (not (.endsWith +version+ "-SNAPSHOT")))))