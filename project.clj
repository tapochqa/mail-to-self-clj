(defproject mail-to-self-clj "2.0.0"
  :description "Mail to self Telegram bot"
  :url "https://t.me/fwd_to_email_bot"

  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [environ             "1.1.0"]
                 [morse               "0.4.3"]
                 [com.draines/postal  "2.0.5"]
                 [io.forward/yaml "1.0.11"]]

  :plugins [[lein-environ "1.1.0"]
            [lein-resource "17.06.1"]]

  :prep-tasks ["javac" "compile" "resource"]
  :hooks [leiningen.resource]
  :resource {

    :resource-paths [ ["resources" 
                     {:target-path "target" ;; directory to store files          
                     }]]
    :update   false      ;; if true only process files with src newer than dest
    :includes [ #".*" ]  ;; optional - this is the default
    :excludes [ #".*~" ] ;; optional - default is no excludes which is en empty vector
    :silent false ;; if true, only print errors
    :verbose true ;; if true, print debugging information
  }

  :main ^:skip-aot mail-to-self-clj.core
  :target-path "target"

  :uberjar-name "mail-to-self-clj-standalone.jar"

  :profiles {:uberjar {:aot :all}})