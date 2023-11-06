(ns invoice-spec
  (:require [schema.core :as s]))

(s/defschema ::tax
             (s/keys :req [::tax-id ::tax-category ::tax-rate]))

(s/defschema ::retention
             (s/keys :req [::retention-id ::retention-category ::retention-rate]))

(s/defschema ::invoice-item
             (s/keys :req [::invoice-item-id ::invoice-item-sku ::taxable/taxes ::retentionable/retentions]))

(s/defschema ::invoice
             (s/keys :req [::invoice-id ::invoice/items]))

