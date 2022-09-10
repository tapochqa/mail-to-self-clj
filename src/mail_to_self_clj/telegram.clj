(ns mail-to-self-clj.telegram
  (:require [morse.handlers :as h]
            [morse.polling :as p]
            [morse.api :as t]
            [clojure.core.async :refer [<!!]]
            [mail-to-self-clj.mail :as mail]
            [yaml.core :as yaml]
            [mail-to-self-clj.credentials :as credentials])
  (load "mail"))


(h/defhandler handler

  (h/command-fn "start"
    (fn [{{id :id :as chat} :chat}]
      (println "Bot joined new chat: " chat)
      (t/send-text credentials/token id (format "Все сообщения идут на %s." credentials/mail-address))))

  (h/message-fn
    (fn [{{id :id} :chat :as message}]
      (clojure.pprint/pprint message)
      (mail/-send message credentials/token )
      (t/send-text credentials/token id "ок")
      )))

(defn start [] (<!! (p/start credentials/token handler)))