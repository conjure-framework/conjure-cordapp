(ns conjure.core-test
  (:require [clojure.test :refer :all]
            [conjure.core :refer :all]))

(deftest fail-unless-macro
  (testing "fail-unless macro expansion"
    (is (true? (fail-unless (= 1 1) "Never happens")))
    (try
      (fail-unless (= 1 0) "Simple message")
      (catch IllegalArgumentException e
        (is (= "Simple message" (.getMessage e)))))
    (try
      (fail-unless (= 1 0) "Message with " 3 " parts")
      (catch IllegalArgumentException e
        (is (= "Message with 3 parts" (.getMessage e)))))))

(deftest fail-if-macro
  (testing "fail-if macro expansion"
    (is (false? (fail-if (= 1 0) "Never happens")))
    (try
      (fail-if (= 1 1) "Simple message")
      (catch IllegalArgumentException e
        (is (= "Simple message" (.getMessage e)))))
    (try
      (fail-if (= 1 1) "Message with " 3 " parts")
      (catch IllegalArgumentException e
        (is (= "Message with 3 parts" (.getMessage e)))))))
