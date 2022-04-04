(ns edu.uidaho.eod.recevent
  (:gen-class)
  (:require [com.brunobonacci.mulog :as mulog]
            #_[pod.babashka.go-sqlite3 :as sqlite]))


(defn set-global-exception-hook
  "Catch any uncaught exceptions and print them."
  []
  (Thread/setDefaultUncaughtExceptionHandler
    (reify Thread$UncaughtExceptionHandler
      (uncaughtException
        [_ thread ex]
        (println "uncaught exception" :thread (.getName thread) :desc ex)))))


(defn -main
  "entry point to app."
  [& _]
  (set-global-exception-hook)
  (mulog/start-publisher! {:type :console})
  (mulog/log ::app-start :msg "starting...")
  (println "app started.")
  #_(println (sqlite/query ":memory:" ["SELECT 1 + 1 AS sum"]))

  (System/exit 0))
