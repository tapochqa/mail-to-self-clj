(ns mail-to-self-clj.mail
    (:require   [postal.core :as postal]
                [mail-to-self-clj.messages :as m]))

(def my-email "tapochqa@yandex.ru")

(def yandex-mailbox {:host "smtp.yandex.ru"
                     :port 587
                     :user my-email
                     :pass (slurp "resources/mail-password.txt")
                     :auth "plain"
                     :domain "limonadny.ru"
                     :tls true})

; didn't use begin
(defn subst [msg len] (subs msg 0 (- len 3)))
(defn apply-dots [msg len] (apply str (subst msg len) "..."))

(defn shorten-message [msg len] 
    (if (>= (count msg) len) 
            (apply-dots msg len)
            msg))
; didn't use end

(defn test-message 
    [-type] 
    (first (map :content 
                (filter 
                    (fn [a] 
                        (= (:type a) 
                            -type)) 
                    m/test-messages))))

(defn null->'' [string] 
    (if (= nil string)
        (str "")
        string))

(defn text-or-caption 
    [{text :text :as message}]
    (or text
        (last (find message :caption))
        ))

(defn map-message 
    ([name username message]
       {:name (null->'' name)
        :username (null->'' username)
        :text (text-or-caption message)})
    ([first-name last-name username message]
       {:name (format   "%s %s" 
                        (null->'' first-name) 
                        (null->'' last-name))
        :username username
        :text (text-or-caption message)}))

(defn if-channel 
    [{{ title :title 
        username :username 
        :as forward} :forward_from_chat :as message}]
    (if (some? forward)
        (map-message 
            title
            username
            message)))

(defn if-open-dm 
    [{{ first-name :first_name 
        last-name :last_name 
        username :username 
        :as forward} :forward_from :as message}]
    (if (some? forward) 
           (map-message
                first-name
                last-name
                username
                message)))

(defn if-closed-dm 
    [{  name :forward_sender_name  
        :as message}] 
    (if (some? name)
            (map-message
                name
                (str "")
                message)))

(defn if-straight 
    [{{ first-name :first_name
        last-name :last_name
        username :username 
        :as from} :from :as message}]
    (map-message
        first-name
        last-name
        username
        message))

(defn seek [message] 
    (or (if-channel message)
        (if-open-dm message)
        (if-closed-dm message)
        (if-straight message)))

(defn compile-theme [message]
    (def a (seek message))
    (format "%s (@%s): %s" 
            (:name a) 
            (:username a) 
            (:text a)))

(defn test-all [] 
    (map compile-theme 
        (map test-message 
            '(  "dm-open" 
                "dm-close" 
                "channel-anon" 
                "channel-signed" 
                "straight"))))


(defn send-message [message] (postal/send-message yandex-mailbox
                                                  {:from my-email
                                                   :to "max@limonadny.ru"
                                                   :subject (compile-theme message)
                                                   :body (text-or-caption message)}))
