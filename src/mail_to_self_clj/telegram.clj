(ns mail-to-self-clj.telegram
  (:require [morse.handlers :as h]
            [morse.polling :as p]
            [morse.api :as t]
            [clojure.core.async :refer [<!!]]
            [mail-to-self-clj.mail :as mail])
  (load "mail"))

(def token (slurp "resources/token.txt"))


(h/defhandler handler

  (h/command-fn "start"
    (fn [{{id :id :as chat} :chat}]
      (println "Bot joined new chat: " chat)
      (t/send-text token id "Все сообщения идут на max@limonadny.ru.")))

  (h/message-fn
    (fn [{{id :id} :chat :as message}]
      (println "Intercepted message: " (clojure.pprint/pprint message))
      (mail/send-message message)
      (t/send-text token id "✉️👌🏻"))))

(defn start [] (<!! (p/start token handler)))