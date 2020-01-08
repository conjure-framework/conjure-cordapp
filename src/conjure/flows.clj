(ns conjure.flows
  (:import (net.corda.core.flows FlowLogic)
           (net.corda.core.transactions TransactionBuilder)
           (net.corda.core.contracts ContractState Command)
           (net.corda.core.node ServiceHub)))

(defn notary-identities [this]
                   (let [hub (.getServiceHub this)]
                     (-> hub .getNetworkMapCache .getNotaryIdentities)))

(defn our-identity [this]
              (.getOurIdentity this))

(defn build-and-sign-transaction [this notary inputs outputs commands]
                            (let [^ServiceHub hub (.getServiceHub this)
                                  builder (-> (TransactionBuilder. notary))]
                              (doseq [^ContractState o outputs]
                                (.addOutputState builder o))
                              (doseq [^Command c commands]
                                (.addCommand builder c))
                              (.verify builder hub)
                              (.signInitialTransaction hub builder)))

(defn subflow [^FlowLogic this ^FlowLogic subflow]
         (.subFlow this subflow))
