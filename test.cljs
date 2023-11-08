(ns dataico.test
  (:require [dataico.invoice-item :as invoice-item]
            [clojure.test :refer :all]))

(deftest test-subtotal-without-discount
         (is (= 100.0 (invoice-item/subtotal {:price 100.0 :quantity 1}))))

(deftest test-subtotal-with-discount
         (is (= 90.0 (invoice-item/subtotal {:price 100.0 :quantity 1 :discount-rate 0.1}))))

(deftest test-subtotal-multiple-quantity
         (is (= 300.0 (invoice-item/subtotal {:price 100.0 :quantity 3}))))

(deftest test-subtotal-zero-quantity
         (is (= 0.0 (invoice-item/subtotal {:price 100.0 :quantity 0}))))

(deftest test-subtotal-negative-price
         (is (= 0.0 (invoice-item/subtotal {:price -100.0 :quantity 2}))))