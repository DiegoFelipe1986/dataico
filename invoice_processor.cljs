(require '[clojure.edn :as edn])

(def invoice (edn/read-string (slurp "invoice.edn")))

(defn find-satisfying-items [invoice]
      (->> invoice
           :invoice/items
           (filter (fn [item]
                       (and (or (some (fn [i] (= 19 (:tax/rate i))) (:taxable/taxes item))
                                (some (fn [i] (= 1 (:retention/rate i))) (:retentionable/retentions item)))
                            (not (and (some (fn [i] (= 19 (:tax/rate i))) (:taxable/taxes item))
                                      (some (fn [i] (= 1 (:retention/rate i))) (:retentionable/retentions item)))))))
           ))

      (def satisfying-items (find-satisfying-items invoice))
      (println satisfying-items)
