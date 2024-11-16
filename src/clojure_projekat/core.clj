(ns clojure-projekat.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "My closet"))

;definisanje odevnih komada
(def majica-bela {:naziv "Bela majica" :tip :gornji-deo :boja :bela :sezona :leto})
(def majica-zelena {:naziv "Green T-shirt" :tip :gornji-deo :boja :zelena :sezona :leto})
(def dzemper-crni {:naziv "Black sweatshirt" :tip :gornji-deo :boja :zelena :sezona :zima})
(def pantalone-crne {:naziv "Crne pantalone" :tip :donji-deo :boja :crna :sezona :univerzalno})
(def farmerke-plave {:naziv "Plave farmerke" :tip :donji-deo :boja :plava :sezona :univerzalno})
(def patike-bele {:naziv "Bele patike" :tip :obuca :boja :bela :sezona :leto})
(def bez-cizme {:naziv "Bez cizme" :tip :obuca :boja :bez :sezona :zima})
(def jakna-zimska-crna {:naziv "Crna zimska jakna" :tip :jakna :boja :crna :sezona :zima})

(def odevni-komadi [majica-bela pantalone-crne patike-bele jakna-zimska-crna majica-zelena dzemper-crni farmerke-plave bez-cizme])

;definisanje pravila za boje - svaka boja ima set boja sa kojima se slaze
(def pravila-boja
  {:bela  #{:univerzalno}
   :crna  #{:univerzalno}
   :plava #{:bela :crna :bez :siva}
   :bez   #{:univerzalno}})

;provera da li se boje slazu
(defn boje-se-slazu? [boja1 boja2]
  (or (= (get pravila-boja boja1 #{}) #{:univerzalno})
      (= (get pravila-boja boja2 #{}) #{:univerzalno})
      (contains? (get pravila-boja boja1 #{}) boja2)))

;provera da li se sezone podudaraju
(defn sezone-iste? [komad1 komad2]
  (or (= (:sezona komad1) :univerzalno)
      (= (:sezona komad2) :univerzalno)
      (= (:sezona komad1) (:sezona komad2))))

;provera da li je kombinacija validna na osnovu boje, sezone i tipa
(defn kombinacija-validna? [komad1 komad2]
  (and (boje-se-slazu? (:boja komad1) (:boja komad2))
       (sezone-iste? komad1 komad2)
       (not= (:tip komad1) (:tip komad2))))

;provera validnosti kombinacije sa vise odevnih komada odjednom
(defn kombinacija-vise-komada? [odevni-komadi]
  (every? (fn [[komad1 komad2]]
            (kombinacija-validna? komad1 komad2))
          (for [x odevni-komadi y odevni-komadi :when (not= x y)]
            [x y])))

(defn recommendation [odevni-komadi sez]
  (let [filtrirani (filter #(or (= (:sezona %) sez)
                                (= (:sezona %) :univerzalno))
                           odevni-komadi)]
    (filter (fn [kombinacija]
              (every? (fn [[odev1 odev2]]
                             kombinacija-validna? odev1 odev2)
                           (for [x kombinacija, y kombinacija :when (not= x y)]
                             [x y])))
              (partition 3 filtrirani))))

(def kombinacije
  (recommendation odevni-komadi :zima))

(doseq [komb kombinacije]
  (println "Predlozena kombinacija:")
  (doseq [komad komb]
    (println (:naziv komad))))
