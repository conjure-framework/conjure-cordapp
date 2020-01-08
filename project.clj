(defproject com.getconjure/conjure-cordapp "0.1.0-SNAPSHOT"
  :description "Framework for developing CorDapps with idiomatic Clojure"
  :url "http://getconjure.com/"
  :license {:name "Apache-2.0"
            :url "https://opensource.org/licenses/Apache-2.0"}
  :repositories [["corda" "https://ci-artifactory.corda.r3cev.com/artifactory/corda"]
                 ["jitpack" "https://jitpack.io"]]
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [net.corda/corda-core "4.3"]
                 [net.corda/corda-node-api "4.3"]
                 [net.corda/corda "4.3"]]
  :repl-options {:init-ns conjure.core}

  ; Conjure flows framework classes have to be AOT-compiled and included in the jar
  ; in order to avoid Kryo deserialization errors on a cold node. It may be possible
  ; to avoid this with a custom deserializer triggering the AOT in the node JVM
  :aot [conjure.flows])
