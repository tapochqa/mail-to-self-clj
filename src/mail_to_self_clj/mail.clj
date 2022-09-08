(ns mail-to-self-clj.mail
	(:require 	[postal.core :as postal]
				[mail-to-self-clj.messages :as m]))

(def my-email "tapochqa@yandex.ru")

(def yandex-mailbox {	:host "smtp.yandex.ru"
						:port 587
						:user my-email
						:pass (slurp "resources/mail-password.txt")
						:auth "plain"
						:domain "limonadny.ru"
						:tls true
						})

; didn't use begin

(defn subst [msg len] (subs msg 0 (- len 3)))
(defn apply-dots [msg len] (apply str (subst msg len) "..."))

(defn shorten-message [msg len] 
	(if (>= (count msg) len) 
			(apply-dots msg len)
			msg))

; didn't use end


(def needed-message (first (map :content (filter (fn [a] (= (:type a) "channel-signed") ) m/test-messages))))

(defn text-or-caption [message]
	(or (:text message)
		(last (find message :caption))))

(defn if-channel [message else-fn]
	(if (:forward_from_chat message) 
			{:name (:title (:forward_from_chat message))
			 :username (:username (:forward_from_chat message))
			 :text (text-or-caption message)}
		else-fn))

(defn if-open-dm [message else-fn]
	(if 	(:forward_from message) 
			{	:name (format 	"%s %s"
								(:first_name (:forward_from message))
								(:last_name (:forward_from message)))
				:username (:username (:forward_from message))
				:text (text-or-caption message)}
			else-fn))

(defn if-closed-dm [message else-fn] 
	(if (:forward_sender_name message)
		{	:name (:forward_sender_name message)
			:text (text-or-caption message)}
		else-fn))

(defn if-straight [message]
	{	:name (format 	"%s %s"
								(:first_name (:forward_from message))
								(:last_name (:forward_from message)))
		:username (:username (:from message))
		:text (text-or-caption message)})

(defn seek [message] 
	(or (if-channel message nil)
		(if-open-dm message nil)
		(if-closed-dm message nil)
		(if-straight message))) 


(defn compile-theme [message]
	(def a (seek message))
	(format "%s (@%s): %s" 
			(:name a) 
			(:username a) 
			(:text a)))

(compile-theme needed-message)

(defn send-message [message] (postal/send-message yandex-mailbox
										               	{:from my-email
										                 :to "max@limonadny.ru"
										                 :subject (compile-theme message)
										                 :body (text-or-caption message)}))
