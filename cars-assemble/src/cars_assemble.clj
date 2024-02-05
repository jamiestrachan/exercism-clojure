(ns cars-assemble)

(defn raw-production-rate
  [speed]
  (* speed 221))

(defn success-rate
  [speed]
  (cond (== speed 10) 0.77
        (== speed 9) 0.8
        (> speed 4) 0.9
        (> speed 0) 1.0
        :else 0.0
        )
)

(defn production-rate
  "Returns the assembly line's production rate per hour,
   taking into account its success rate"
  [speed]
  (* (raw-production-rate speed) (success-rate speed))
  )

(defn working-items
  "Calculates how many working cars are produced per minute"
  [speed]
  (int (/ (production-rate speed) 60))
  )
