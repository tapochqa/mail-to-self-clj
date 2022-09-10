(ns mail-to-self-clj.mail
    (:require   [postal.core :as postal]
                [mail-to-self-clj.process :as process]
                [mail-to-self-clj.credentials :as credentials]
                ))

(defn send-message
    [message token {user :user :as mailbox}] 
    (postal/send-message mailbox
                    {   :from user
                        :to user
                        :subject (process/compile-theme message)
                        :body (process/process-body message token)}))

(defn -send [message token]
    (send-message message token credentials/mailbox))