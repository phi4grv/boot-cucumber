(ns vonrosen.boot-cucumber
  {:boot/export-tasks true}
  (:require [boot.pod  :as pod]
            ;[boot.task.built-in :refer [target]]
            [boot.core :as core]
            [cemerick.pomegranate :as pom]
            [clojure.test :refer :all]
            ;[adzerk.boot-test :refer [test] :rename {test test*}]
            ;[clojure.java.io :as io]
            ;[clojure.tools.namespace.find :refer [find-namespaces-in-dir]]
            )
  (:import [cucumber.api.cli Main])
  #_(:use clojure.test))

;(load-data-readers!)

(def task-args (atom nil))

(core/deftask cukes
  "Run cucumber tests."
  [g glue VALUE str "Where glue code (step definitions, hooks and plugins) are loaded from." 
   p plugin VALUE str "Register a plugin. Built-in formatter PLUGIN types: junit,
                                         html, pretty, progress, json, usage, rerun,
                                         testng. Built-in summary PLUGIN types:
                                         default_summary, null_summary. PLUGIN can
                                         also be a fully qualified class name, allowing
                                         registration of 3rd party plugins." 
   t tags VALUE str "Only run scenarios tagged with tags matching
                                         TAG_EXPRESSION."
   n name VALUE str "Only run scenarios whose names match REGEXP."
   d dry-run bool "Skip execution of glue code."
   m monochrome bool "Don't colour terminal output."
   s strict bool "Treat undefined and pending steps as errors."
   v version bool "Print version."
   i i18n VALUE str " List keywords for in a particular language. Run with \"--i18n help\" to see all languages"]
  (let [args (vec (flatten 
                     (remove nil? (conj [] 
                                        (if glue
                                          ["--glue" glue]
                                          ["--glue" "features"])
                                        (if plugin
                                          ["--plugin" plugin])
                                        (if tags
                                          ["--tags" tags])
                                        (if name
                                          ["--name" name])
                                        (if dry-run
                                          ["-d"])
                                        (if monochrome
                                          ["-m"])
                                        (if strict
                                          ["-s"])
                                        (if version
                                          ["--version"])
                                        (if i18n
                                          ["--i18n" i18n])
                                        ["features"]))))]
    (core/with-pre-wrap fileset
          #_(prn ~@(->> fileset
                                     core/input-dirs
                                     (map (memfn getPath))))
          #_(prn        
             (pod/with-eval-in (worker-pods :refresh)
               (distinct (mapcat #(find-namespaces-in-dir (io/file %)) 
                                 ~@(->> fileset
                                     core/input-dirs
                                     (map (memfn getPath)))))
              
               #_(all-ns* ~@(->> fileset
                                                             core/input-dirs
                                                             (map (memfn getPath))))))
          (reset! task-args args)
          ;(prn @task-args)          
          ;(test* :include #".*vonrosen.*$")
          ;(core/add-source fileset (io/file "features/step_definitions"))
          ;(pom/add-classpath "features")          
          (pom/add-classpath "features/step_definitions")
          (run-tests (symbol "vonrosen.boot-cucumber"))                    
          (core/commit! fileset)
    
        ;(test* :include #".*ui-tests.*$" :startup '#{vonrosen.boot-cucumber/cucumber-tests})    
        #_(run-tests (symbol "vonrosen.boot-cucumber"))    
        #_(test* :include #"^.*src.*$")    
        #_(test* :namespaces #{(symbol "vonrosen.boot-cucumber")})
        #_(test* :include #".*ui-tests.*$"))))

(deftest cucumber-tests  
  (prn @task-args)
  ;(core/merge-env! :source-paths #{"ui_tests/clj" "features"})
  ;(core/merge-env! :resource-paths #{"features"})  
  (. cucumber.api.cli.Main (main (into-array @task-args))))