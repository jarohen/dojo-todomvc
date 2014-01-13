(ns todomvc.cljs.app
    (:require [clojure.string :as s]
              clojure.browser.repl
              [cloact.core :as cloact :refer [atom]])
    (:require-macros [dommy.macros :refer [sel sel1]]))

(defn child [props]
  [:p
   "Hi, I am " (:name props)])

(defn childcaller []
  [child {:name "Dave"}])

(set! (.-onload js/window)
      (fn []
        (cloact/render-component [childcaller]
                                 (.-body js/document))))


