(ns vonrosen.boot-cucumber
  (:require   [boot.core :as core])  
  (:import [cucumber.api.cli Main]))

(core/deftask cukes
  ""
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
  (let [task-args (conj []
                        (if glue
                          (str "--glue" " " glue)
                          #_(str "--glue" " " "features/step_definitions" " " "features"))
                        (if plugin
                          (str "--plugin" " " plugin))
                        (if tags
                          (str "--tags" " " tags))
                        (if name
                          (str "--name" " " name))
                        (if dry-run
                          "-d")
                        (if monochrome
                          "-m")
                        (if strict
                          "-s")
                        (if version
                          "--version")
                        (if i18n
                          (str "--i18n" " " i18n)))]
    (. cucumber.api.cli.Main (main (into-array task-args)))))