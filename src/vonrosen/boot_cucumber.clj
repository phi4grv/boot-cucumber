(ns vonrosen.boot-cucumber
  {:boot/export-tasks true}
  (:require [boot.core :as core]
            [cemerick.pomegranate :as pom]
            [clojure.test :refer :all])
  (:import [cucumber.api.cli Main]))

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
   i i18n VALUE str " List keywords for in a particular language. Run with \"--i18n help\" to see all languages"
   f feature-dir VALUE str "Directory in which feature files live."]
  (let [args (cond-> ["--glue" glue]
               plugin (concat ["--plugin" plugin])
               tags (concat ["--tags" tags])
               name (concat ["--name" name])
               i18n (concat ["--i18n" i18n])
               dry-run (conj "-d")
               monochrome (conj "-m")
               strict (conj "-s")
               version (conj "--version")
               true (conj feature-dir)
               true vec)]
    (core/with-pre-wrap fileset
      (reset! task-args args)
      (if glue
        (pom/add-classpath glue)
        (pom/add-classpath "features"))
      (run-tests (symbol "vonrosen.boot-cucumber"))
      (core/commit! fileset))))

(core/task-options!
 cukes {:glue "features"
        :feature-dir "features"})

(deftest cucumber-tests
  (. cucumber.api.cli.Main (main (into-array @task-args))))
