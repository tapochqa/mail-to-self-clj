(ns mail-to-self-clj.repl
	(:require [mail-to-self-clj.telegram :as telegram])
    (load "telegram")
    (load "mail")
    (load "process")
    (load "messages")
    (load "credentials"))

(comment
	(telegram/start))
