(ns todomvc.cljs.app
    (:require [clojure.string :as s]
              clojure.browser.repl)
    (:require-macros [dommy.macros :refer [sel sel1]]))

(set! (.-onload js/window)
      (fn []
        ))


