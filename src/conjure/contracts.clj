(ns conjure.contracts
  (:require [conjure.core :as core])
  (:import (net.corda.core.transactions LedgerTransaction)
           (net.corda.core.contracts StateAndRef TransactionState CommandWithParties)))

(defn -bean-or-record-as-is [obj]
  (if (record? obj) obj (bean obj)))

(extend-protocol core/Destructurable
  LedgerTransaction
  (destructure-object [this]
    {:commands   (lazy-seq (map core/destructure-object (.getCommands this)))
     :inputs     (lazy-seq (map core/destructure-object (.getInputs this)))
     :outputs    (lazy-seq (map core/destructure-object (.getOutputs this)))
     :references (lazy-seq (map core/destructure-object (.getReferences this)))})

  StateAndRef
  (destructure-object [this]
    (merge (-bean-or-record-as-is (.getState this)) {:ref (.getRef this)}))

  TransactionState
  (destructure-object [this]
    (merge (-bean-or-record-as-is (.getData this))
           {:contract    (symbol (.getContract this))
            :notary      (.getNotary this)
            :encumbrance (.getEncumbrance this)
            :constraint  (.getConstraint this)}))

  CommandWithParties
  (destructure-object [this]
    (merge (-bean-or-record-as-is (.getValue this))
           {:signing-keys    (lazy-seq (.getSigners this))
            :signing-parties (lazy-seq (.getSigningParties this))})))
