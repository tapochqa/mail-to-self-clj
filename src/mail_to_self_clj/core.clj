(ns mail-to-self-clj.core
  (:require [clojure.string :as str]
            [environ.core :refer [env]]
            [mail-to-self-clj.telegram :as telegram]
            [mail-to-self-clj.credentials :as credentials])
    (:gen-class))


(defn -main
    [& args]
    (when (str/blank? credentials/token)
    (println "Please provde token in TELEGRAM_TOKEN environment variable!")
    (System/exit 1))
    (println "Starting the mail-to-self-clj")
    (telegram/start)
    )