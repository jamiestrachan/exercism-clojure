(ns squeaky-clean
  (:require [clojure.string :as str]))

(defn clean-char
  [c]
  (cond
    (Character/isWhitespace c) "_"
    (Character/isISOControl c) "CTRL"
    (not (Character/isLetter c)) ""
    (and (<= (Character/compare \α c) 0) (>= (Character/compare \ω c) 0)) ""
    :else c
    ))

(defn kebab-to-camel
  [s]
  (str/replace s #"\-(.)" #(.toUpperCase (%1 1)))
  )

(defn clean
  "TODO: add docstring"
  [s]
  (str/join (map clean-char (seq (kebab-to-camel s))))
  )
