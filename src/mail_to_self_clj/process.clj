(ns mail-to-self-clj.process
    (:require   [mail-to-self-clj.messages :as m]
                [morse.api :as t-api]
                [clojure.java.io :as io]
                [clj-http.client :as client]))

; didn't use begin
(defn subst [msg len] (subs msg 0 (- len 3)))
(defn apply-dots [msg len] (apply str (subst msg len) "..."))

(defn shorten-message [msg len] 
    (if (>= (count msg) len) 
            (apply-dots msg len)
            msg))
; didn't use end

(def token (slurp "resources/token.txt"))

(defn test-message 
    [-type] 
    (first (map :content 
                (filter 
                    (fn [a] 
                        (= (:type a) 
                            -type)) 
                    m/test-messages))))

(defn text-or-caption 
    [{text :text :as message}]
    (str (or text
                    (last (find message :caption)))))

(defn map-message 
    ([name username message]
       {:name (str name)
        :username (str username)
        :text (text-or-caption message)})
    ([first-name last-name username message]
       {:name (str first-name (if last-name (str " " last-name) nil))
        :username (str username)
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


(defn find-body-key [message]
    (map 
        (fn [a b]  (find a b)) 
        (repeat 8 message) 
        '(:photo :text :contact :sticker :video :video_note :voice :audio)))

(defn untextize [string]
    (subs (clojure.string/replace string #"text" "") 1))

(defn media-type [message]
    (->> message
        find-body-key
        (remove nil?)
        flatten
        first
        str
        untextize))


(defn seek [message]
    (conj {:media_type (media-type message)}
            (or (if-channel message)
            (if-open-dm message)
            (if-closed-dm message)
            (if-straight message))))
    
(defn compile-theme [message]
    (let [{name :name username :username text :text media :media_type} (seek message)]
        (format "%s (@%s): %s %s" 
                name 
                username
                (str media)
                text)))

(defn tg-link 
    [token message {id :file_id size :file_size}]
    (if (< size 20000000)
        

        [{  :type :attachment
            :content (str "https://api.telegram.org/file/bot"
                                                token
                                                "/"
                                                (:file_path (:result (t-api/get-file token id)))
                                                )}
        {   :type "text/plain; charset=utf-8"
            :content (str (text-or-caption message))}]

        [{  :type "text/plain; charset=utf-8"
            :content (str (text-or-caption message) " 🚫✉️ файл больше 20-ти мегабайт.")}]))


(defn media->link [token message coll]
    (if (= (type coll) java.lang.String)
        [{   :type "text/plain; charset=utf-8"
            :content coll}]
        (tg-link token message coll)))

(defn process-body [message token]
    (->> message
            find-body-key
            (remove nil?)
            flatten
            (drop 1)
            (sort-by :file_size)
            last
            (media->link token message)))

(comment
    (media-type (test-message "photo"))
    (seek (test-message "photo"))
    (media-type (test-message "dm-close"))
    (first (process-body (test-message "photo") token))
    (process-body (test-message "straight") token)
    (process-body (test-message "channel-signed") token)
    (text-or-caption (test-message "photo"))
    (map compile-theme 
        (map test-message 
            '(  "straight"
                "photo"
                "dm-open" 
                "dm-close" 
                "channel-anon" 
                "channel-signed"))))

