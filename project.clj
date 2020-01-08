(defproject conjure "0.1.0-SNAPSHOT"
  :description "Framework for developing CorDapps with idiomatic Clojure"
  :url "http://getconjure.com/"
  :repositories [["corda" "https://ci-artifactory.corda.r3cev.com/artifactory/corda"]
                 ["jitpack" "https://jitpack.io"]]
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [net.corda/corda-core "4.3"]
                 [net.corda/corda-node-api "4.3"]
                 [net.corda/corda "4.3"]]
  :repl-options {:init-ns conjure.core})
