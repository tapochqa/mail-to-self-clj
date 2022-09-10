(ns mail-to-self-clj.mail
    (:require   [postal.core :as postal]
                [mail-to-self-clj.process :as process]
                [yaml.core :as yaml]))

(def my-email "tapochqa@yandex.ru")

(def yandex-mailbox {:host "smtp.yandex.ru"
                     :port 587
                     :user my-email
                     :pass (slurp "resources/mail-password.txt")
                     :auth "plain"
                     :domain "limonadny.ru"
                     :tls true})

(defn send-message [message token] (postal/send-message yandex-mailbox
                                                  {:from my-email
                                                   :to "max@limonadny.ru"
                                                   :subject (process/compile-theme message)
                                                   :body (process/process-body message token)}))



(clojure.java.io/file "resources/token.txt")


(yaml/from-file "credentials.yml")