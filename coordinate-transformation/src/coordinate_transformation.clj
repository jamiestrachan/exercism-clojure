(ns coordinate-transformation)

(defn translate2d
  "Returns a function making use of a closure to
   perform a repeatable 2d translation of a coordinate pair."
  [dx dy]
  (fn [startx starty] (vector (+ startx dx) (+ starty dy))))
  

(defn scale2d 
  "Returns a function making use of a closure to
   perform a repeatable 2d scale of a coordinate pair."
  [sx sy]
  (fn [startx starty] (vector (* startx sx) (* starty sy))))
  

(defn compose-transform
  "Create a composition function that returns a function that 
   combines two functions to perform a repeatable transformation."
  [f g]
  (fn [& args] (apply g (apply f args))))
  
(defn memoize-transform
  "Returns a function that memoizes the last result.
   If the arguments are the same as the last call,
   the memoized result is returned."
  [f]
  (let [memo (atom {})] ;; set up memo
    (fn [& args]
      (if (contains? @memo args) ;; check for relevant entry in memo given args 
        (get @memo args) ;; return result 
        (let [retval (apply f args)]
          (swap! memo assoc args retval) ;; save return vale
          retval
          )
        )
      )
    )
  )
