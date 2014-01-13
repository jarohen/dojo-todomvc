(ns todomvc.cljs.app
    (:require [clojure.string :as s]
              clojure.browser.repl
              [cloact.core :as cloact :refer [atom]])
    (:require-macros [dommy.macros :refer [sel sel1]]))

(def example-todos
  [{:caption "Clean the bathroom" :done? true :id 0}
   {:caption "Clean the kitchen" :done? false :id 1}])

(def !last-id (atom 1))

(def !todos (atom example-todos))

(defn update-entry [idx]
  (let [f #(update-in % [idx :done?] not)]
    (swap! !todos f)
    )

  )

(defn todo-component []
  [:div.row
   [:ul {:style {:list-style-type :none
                 :margin-top "3em"}}
    (js/console.log (pr-str @!todos))
    (for [[idx {:keys [done? caption]}] (map vector (range) @!todos)]
      [:li {:key idx :style (when done? {:text-decoration :line-through})}
       ;;       [:input {:type "checkbox" :checked done? :on-change #(swap! !todos update-in [idx :done?] not)}]
       [:input {:type "checkbox" :checked done? :on-change #(update-entry idx)}]
;;       [:input {:type "checkbox" :checked done? :on-change #(swap! !todos inc)}]
       [:span {:style {:margin-left "1em"}} caption]])]])

(defn add-task! [task]
  (swap! !todos conj {:caption task :done? false}))



(set! (.-onload js/window)
      (fn []
        (cloact/render-component [todo-component]
                                 (.-body js/document))))


