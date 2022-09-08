(ns mail-to-self-clj.core
  (:require [clojure.string :as str]
            [environ.core :refer [env]]
            [mail-to-self-clj.telegram :as telegram])
    (load "telegram")
    (load "mail")
    (:gen-class))


(defn -main
    [& args]
    (when (str/blank? telegram/token)
    (println "Please provde token in TELEGRAM_TOKEN environment variable!")
    (System/exit 1))
    (println "Starting the mail-to-self-clj")
    (telegram/start)
    )