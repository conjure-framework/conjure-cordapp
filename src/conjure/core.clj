(ns conjure.core)

(defmacro fail-unless
  "Evaluates the test form and returns its value if it is truthful,
  otherwise throws IllegalArgumentException with provided message."
  [test & message-parts] `(or ~test (throw (IllegalArgumentException. (str ~@message-parts)))))

(defmacro fail-if
  "Evaluates the test form and returns its value if it is false,
  otherwise throws IllegalArgumentException with provided message."
  [test & message-parts] `(and ~test (throw (IllegalArgumentException. (str ~@message-parts)))))

(defprotocol Destructurable
  "Converts given Java class into a hash-map to allow map destructuring syntax"
  (destructure-object [this]))
