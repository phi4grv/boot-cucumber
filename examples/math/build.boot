(set-env!
  :resource-paths #{"src"}
  :source-paths #{"src" "test"}
  :dependencies   '[[adzerk/boot-test "1.2.0" :scope "test"]
                    [vonrosen/boot-cucumber "1.0.0-SNAPSHOT" :scope "test"]])

(require '[adzerk.boot-test :refer :all]
         '[vonrosen.boot-cucumber :refer :all])

(deftask build []
  (comp
   (pom)
   (jar)
   (install)))