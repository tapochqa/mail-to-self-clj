(defproject mail-to-self-clj "0.1.0"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"

  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [environ             "1.1.0"]
                 [morse               "0.4.3"]
                 [com.draines/postal  "2.0.5"]]

  :plugins [[lein-environ "1.1.0"]]

  :main ^:skip-aot mail-to-self-clj.core
  :target-path "target/%s"

  :profiles {:uberjar {:aot :all}})
