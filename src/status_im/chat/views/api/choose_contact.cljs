(ns status-im.chat.views.api.choose-contact
  (:require-macros [status-im.utils.views :refer [defview]])
  (:require [re-frame.core :as re-frame]
            [status-im.components.contact.contact :refer [contact-view]]
            [status-im.components.list.views :as list]
            [status-im.components.react :as react]))

(defn- render-contact [arg-index bot-db-key]
  (fn [contact]
    [contact-view {:contact  contact
                   :on-press #(re-frame/dispatch
                                [:set-contact-as-command-argument {:arg-index arg-index
                                                                   :bot-db-key bot-db-key
                                                                   :contact contact}])}]))


(defview choose-contact-view [{title      :title
                               arg-index  :index
                               bot-db-key :bot-db-key}]
  [contacts [:contacts-filtered :people-in-current-chat]]
  [react/view {:flex 1}
   [react/text {:style {:font-size      14
                        :color          "rgb(147, 155, 161)"
                        :padding-top    12
                        :padding-left   16
                        :padding-right  16
                        :padding-bottom 12}}
    title]
   [list/flat-list {:data                      contacts
                    :render-fn                 (render-contact arg-index bot-db-key)
                    :enableEmptySections       true
                    :keyboardShouldPersistTaps :always
                    :bounces                   false}]])
