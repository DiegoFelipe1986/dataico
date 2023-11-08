(ns dataico.invoice-loader
  (:require [clojure.data.json :as json]
            [schema.core :as s]
            [invoice-spec :as inv]
            )
  )

(defn load-json-file [file-name]
      (with-open [reader (clojure.java.io/reader file-name)]
                 (json/read (json/parse reader))))


(defn generate-invoice [json-file]
     (let [invoice-data (load-json-file json-file)]
          (if (s/validate ::invoice invoice-data)
            invoice-data
            (throw (ex-info "JSON data does not conform to the invoice spec" {:data invoice-data}))))
)

(defn -main []
      (let [file-name "invoice.json"]
           (try
             (let [invoice-map (generate-invoice file-name)]
                  (println "Invoice generated successfully:")
                  (println invoice-map))
             (catch Exception e
               (println "Error processing the invoice:")
               (println (.getMessage e))))))

(-main)
