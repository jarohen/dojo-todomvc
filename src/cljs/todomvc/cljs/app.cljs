(ns todomvc.cljs.app
    (:require [clojure.string :as s]
              clojure.browser.repl
              [cloact.core :as cloact :refer [atom]])
    (:require-macros [dommy.macros :refer [sel sel1]]))

(def example-todos
  [{:caption "Clean the bathroom" :done? true}
   {:caption "Clean the kitchen" :done? false}])

(def !todos (atom example-todos))

(defn toggle-done [idx]
  (swap! !todos update-in [idx :done?] not)) 

(defn todo-item []
  (fn [{:keys [key todo]}]
    (let [{:keys [caption done?]} todo]
      [:li {:key key :style (cond-> {} done? (assoc :text-decoration :line-through))}
       [:input {:type "checkbox" :checked done? :on-change #(toggle-done key)}]
       [:span {:style {:margin-left "1em"}} caption]])))

(defn todo-component []
  [:div.row
   [:ul {:style {:list-style-type :none
                 :margin-top "3em"}}
    (js/console.log (pr-str @!todos))
    (for [[idx todo] (map vector (range) @!todos)]
      [todo-item {:key idx :todo todo}])]])

(set! (.-onload js/window)
      (fn []
        (cloact/render-component [todo-component]
                                 (.-body js/document))))


