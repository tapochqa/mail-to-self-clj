(ns mail-to-self-clj.credentials
	(:require [yaml.core :as yaml]))

(def token (:token (yaml/from-file "resources/credentials.yml")))
(def mailbox (:mail (yaml/from-file "resources/credentials.yml")))
(def mail-address (:user mailbox))