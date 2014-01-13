(ns todomvc.cljs.app
    (:require [clojure.string :as s]
              clojure.browser.repl
              [cloact.core :as cloact :refer [atom]])
    (:require-macros [dommy.macros :refer [sel sel1]]))

(def click-count (atom 0))

(defn child [props]
  [:p {:on-click #(swap! click-count inc)}
   "I have been clicked " @click-count " times."] )

(defn childcaller []
  [child {:name "Dave"}])

(set! (.-onload js/window)
      (fn []
        (cloact/render-component [childcaller]
                                 (.-body js/document))))


