;;;
					;Destructuring
;;;
				

(defn greet-author1 [author]
  (println "Hello," (:first-name author)))

(greet-author1 {:last-name "Vinge" :first-name "Vernor"})

;;;;;;;;;;;;;;;
;use of a map to destructure an associative coll

(defn greet-author2 [{fname :first-name}]
  (println "Hello," fname))

(greet-author2
 {:last-name "Vinge"
  :first-name "Verner"})

;use a vector to destructure a sequential coll

(let [[x y] [6 7 83]]
  [x y])

(let [[_ _ z] [1 2 3]]
  z)

(let [[x y :as coords]
      [1 2 3 4 5 6]]
  (str " x: " x
       " y: " y
       " total dimensions "
       (count coords)))

(use
 '[clojure.contrib.str-utils :only
   (re-split str-join)])

(defn ellipsize [words]
  (let [[w1 w2 w3] (re-split #"\s+" words)]
    (str-join " " [w1 w2 w3 "..."])))

(ellipsize "The quick brown fox jumps over the lazy dog")

;;; 
;;;namespace
;;;

(def foo 10)
(resolve 'foo)
(in-ns 'myapp)
String


;
;
while learning clojure use clojure.core ns when you move to new ns
;;; 

(clojure.core/use 'clojure.core)
(import '(java.io InputStream File))
File/separator

(require 'clojure.contrib.math)
(clojure.contrib.math/round 1.7)
(round 1.7) ;error
(use 'clojure.contrib.math) ;add :only x
(use '[ clojure.contrib.math
       :only (round)])

(round 1.6)

(use :reload '[ clojure.contrib.math
	       :only (round)])

;;; branch with if

(defn is-small? [number]
  (if (< number 100) "yea"))

(is-small? 50)

(defn is-small? [number]
  (if (< number 100) "yea" "nope"))

(is-small? 500000)

(defn is-small? [number]
  (if (< number 100)
    "yea"
    (do      ;do says -side effect to follow
      (println "saw a big number" number)
      "nope")))

(is-small? 200)

(is-small? 99)

;;; loop /recur

;first loop binds result to [] and x to 5
;x is not zero so- recur rebinds x and result
;result binds to prev result/conj wth prev x
;x binds to dec on prev x

(loop [result [] x 5]
  (if (zero? x)
    result
    (recur (conj result x) (dec x)))) 

(conj [5 4 3 2] 1)

(defn countdown [result x]
  (if (zero? x)
    result
    (recur (conj result x)
	   (dec x))))

(countdown [] 10)

;do the same with seqs

(into [] (take 10 (iterate dec 10)))
(into [] (drop-last (reverse (range 11))))
(vec (reverse (rest (range 11))))

;;; no loops sold here

(defn indexed [coll] (map vector (iterate inc 0)
			  coll))

(indexed "abcdefg")

(defn index-filter [pred coll]
  (when pred
    (for [[idx elt] (indexed coll) :when (pred elt)] idx)))

(index-filter #{\a \b} "abcdbbb")

;;; we only need the first match -so we can...

(defn first-index-match [pred coll]
  (first (index-filter pred coll)))

(first-index-match #{\z \a} "yycdazzzaaa")

;;;
;;; 
;;; M-; with paredit
;;;
	  

      (def x 5)

      (def lst '(a b c)))))
(first lst)
(rest lst)

(def x (rest '(a b c d)))
(println x)

`(fred x ~x lst ~@lst 7 8 :nine)

(defn hello [name] (str "Hello, " name))
(hello "Dougie")
(str *1 " and " *2) ;inserts last two outputZZ
(/ 1 0) ;a no/no throws error
(.printStackTace *e)

;; first program

#{}
(conj #{} "Stu")
(def visitors (ref #{}))
(dosync (alter visitors conj "Stu"))
(deref visitors)
@visitors


(defn hello
  "Writes hello message to *out*. Calls you by username.
Knows if you have been here before."
[username]
(dosync
 (let [past-visitor (@visitors username)]
   (if past-visitor
     (str "Welcome back, " username)
     (do
       (alter visitors conj username)
       (str "Hello, " username))))))
(doc hello)
;; Test
(hello "Rick")
(hello "Rick")
(hello "Dougie")
@visitors


;; this pops a "hello world! window ... cool
(. javax.swing.JOptionPane (showMessageDialog nil "Hello World"))

(doc str) ;; doc strings for Stuff.
(doc hello)
(find-doc "reduce")

(use 'clojure.contrib.repl-utils)



;;Exploring Clojure
;;Clojure Forms

;Boolean 
(> 5 3) ;true
(= 4 4) ;true
(= 4 3) ;false

;Charater    
 \a

;Keyword
:tag
:doc

;List
(123)
(println "foo")

;Map
{:name "Bill" :age 42}

;Nil
nil

;Number
1
23
4.3

;Set
#{:snap :crackle :pop}

;String
"hello"

;Symbol
user/foo
java.lang.String

;Vector
[1 2 3 22]


(concat [1 2] [3 4])

(+ 1 2 3 4)

(+)

(- 10 5)
(- 5 10)

(/ 22 7)
(class (/ 22 7))

(.toUpperCase "hello")

(def s(str 1 2 nil 3))
s

;predicate -function that returns true or false
; true? false? nil? zero?

(true? true) ;true
(false? false) ;true
(true? "foo") ;false

(find-doc #"\?$")


(defn is-small? [number]
  (if (< number 100)
    "yes"
    (do (println "Saw a big number" number)
	"no")))

(is-small? 200)
(is-small? 50)


(loop [result [] x 5]
  (if (zero? x)
    result
    (recur (conj result x) (dec x))))

(defn countdown [result x]
  (if (zero? x)
    result
    (recur (conj result x) (dec x))))
(countdown [] 5)

(into [] (take 5 (iterate dec 5)))

(into [] (drop-last (reverse (range 6))))

(vec (reverse (rest (range 6))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn indexed [coll] (map vector (iterate inc 0) coll))

(indexed "zzabyycdxx")

(defn index-filter [pred coll]
  (when pred
    (for [[idx elt] (indexed coll) :when (pred elt)] idx)))

(index-filter #{\a \b} "zzabyycdxx")

(defn index-of-any [pred coll]
  (first (index-filter pred coll)))

(index-of-any #{\z \a} "zzabyycdxx")
(index-of-any #{\b \y} "zzabyycdxx")

;find the 3rd occurrence of 'heads' in coins flips
(nth (index-filter #{:h} [:t :t :h :t :h :t :t :t :h :h]) 2)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(require 'clojure.contrib.str-utils)

(ancestors (class [1 2 3]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defstruct book :title :author)
(def b (struct book "Anathem" "Neal Stephenson"))
b
(:title b)


(defn greeting
  "Returns a greeting of the form 'Hello, username.'
   Default username is 'world!!'."
([] (greeting "world!!"))
([username]
(str "Hello, " username)))

(greeting "world!")
(greeting)
(doc greeting)


;;;;if;;;;;;;;;

(defn is-small? [number]
  (if (< number 100) 
    "yes"
    
    ;;side effects to follow with 'do'

    (do       
      (println "Saw a BIG number" number)
      "no")))



(is-small? 5000)
(is-small? 50)

;;;;;;;;;;;;;;;;;;end;;;;;;;;;;;;;;;;;;;

;;;;;;;;;;;;;;;;loop/recur;;;;;;;;;;;;;


(loop [result [] x 1000]
  (if (zero? x)
    result
    (recur (conj result x) (dec x))))

;;;or;;;;

(defn countdown [result x]
  (if (zero? x)
    result
    (recur (conj result x) (dec x))))

(countdown [] 5)

;;;;or;;;;;

(into [] (take 5 (iterate dec 5)))

;;;;or;;;;

(into [] (drop-last (reverse (range 6))))

;;;;or;;;;

(vec (reverse (rest (range 6))))

;;;;;;;;end;;;;;;;;

;;;;;;metadata;;;;;;;;;;;;;

; (with-meta object metadata)

(def stu {:name "Stu" :email "stu@example.com"})

(def serializable-stu (with-meta stu {:serializable true}))

(= stu serializable-stu)

(identical? stu serializable-stu)

(meta serializable-stu)

(meta stu)
;;same as ;;;
^stu  ;;does not eval in emacs
;???????

(meta #'str)

; java interop

(new java.util.Random)

(def rnd (new java.util.Random))

(. rnd nextInt)
; or
(. rnd nextInt 10)

(. Math PI) ;java.lang auto loads by default.

(import '(java.util Random Locale)
	'(java.text MessageFormat))

Random

Locale

MessageFormat

;  Syntactic Sugar

(new Random)
; same as
(Random.)

(System/currentTimeMillis)

; java arrays

(make-array String 5)
; or
(seq (make-array String 5))

; doc array functions
(find-doc "-array")


; sequences or seq

(first '(1 2 3))

(rest '(1 2 3))

(cons 0 '(1 2 3))

; works with vectors also

(rest [1 2 3]) ;but returns a seq/ and prints like a list

; etc
;;; incanter stuff

(use '(incanter core stats charts))
(view (function-plot pdf-normal -3 3))


;;;gui stuff

(import '(javax.swing JFrame JPanel JButton))
(def button (JButton. "Click Me!"))
(def panel (doto (JPanel.)
             (.add button)))
(def frame (doto (JFrame. "Hello Frame")
             (.setSize 200 200)
             (.setContentPane panel)
             (.setVisible true)))

(import 'javax.swing.JOptionPane)
(defn say-hello []
  (JOptionPane/showMessageDialog
    nil "Hello, World!" "Greeting"
    JOptionPane/INFORMATION_MESSAGE))

(import 'java.awt.event.ActionListener)
(def act (proxy [ActionListener] []
           (actionPerformed [event] (say-hello))))

(.addActionListener button act)


;; next

(import '(javax.swing JFrame JLabel JTextField JButton)
        '(java.awt.event ActionListener)
        '(java.awt GridLayout))
(defn celsius []
  (let [frame (JFrame. "Celsius Converter")
        temp-text (JTextField.)
        celsius-label (JLabel. "Celsius")
        convert-button (JButton. "Convert")
        fahrenheit-label (JLabel. "Fahrenheit")]
    (.addActionListener convert-button
      (proxy [ActionListener] []
        (actionPerformed [evt]
          (let [c (Double/parseDouble (.getText temp-text))]
            (.setText fahrenheit-label
               (str (+ 32 (* 1.8 c)) " Fahrenheit"))))))

			 
    (doto frame
      (.setLayout (GridLayout. 2 2 3 3))
      (.add temp-text)
      (.add celsius-label)
      (.add convert-button)
      (.add fahrenheit-label)
      (.setSize 300 80)
      (.setVisible true))))

(celsius)


;;;  next

;;;from test clj
((loop [result [] x 5 ]
   (if (zero? x)
     result
     (recur (conj result x) (dec x)))))

					
(merge-with
       concat
       {:rubble ["Barney"] :flintstone [ "Fred"]}
       {:rubble [ "Betty" ] :flintstone [ "Wilma"]}
       {:rubble ["Bam-Bam"] :flintstone [ "Pebbles"]})

;; from my-project core.clj




;;; new file ;;;

(apply hash-map (interleave [:a :b :c] [1 2 3]))

(into {} (map vector [:a :b :c] [1 2 3]))



(def foo 10)
(resolve 'foo)

;;; fleetdb ;;;

;; start db with:

;;   $ java -cp fleetdb-standalone.jar fleetdb.server -f demo.fdb

;; To connect the client to the database:



(use 'fleetdb.client)

(def client (connect {:host "127.0.0.1", :port 3400}))



(client ["ping"])



(client ["insert" "accounts" {"id" 1, "owner" "Eve", "credits" 100}])




(client ["insert" "accounts"
          [{"id" 2, "owner" "Bob", "credits" 150}
           {"id" 3, "owner" "Dan", "credits" 50}
           {"id" 4, "owner" "Amy", "credits" 1000, "vip" true}]])


(client ["select" "accounts" {"where" ["=" "id" 2]}])

(client ["select" "accounts"])


(client ["insert" "accounts"
          [{"id" 52, "owner" "Fred", "credits" 2150}
           {"id" 53, "owner" "Ethel", "credits" 550}
           {"id" 54, "owner" "Lucie", "credits" 14000, "vip" false}]])


(client ["update" "accounts" {"credits" 55} {"where" ["=" "owner" "Bob"]}])




;;; concurrency

;;; vars

(def foo {})
foo

(def foo {:name "Craig" :age 38})

(defn print-foo
  ([] (print-foo ""))
  ([prefix] (println prefix foo)))

(binding [foo 3] (print-foo))

;;java interop

(import [java.lang Thread])
(defn with-new-thread [f]
  (.start (Thread. f)))

(with-new-thread (fn [] (print-foo "new thread"))) ;does not compute /only returns nil

(do
  (binding [foo "keith"]
    (with-new-thread
      (fn [] (print-foo "background:")))
    (print-foo "foreground1:"))
  (print-foo "foreground2:"))  ;only prints forground1 and 2 not background ????

					;references

					;atoms/agents/refs

(def foo (atom {:blah "this"}))

(swap! foo (fn [old] 3))
(swap! foo inc)

(pmap
 (fn [_] (swap! foo inc))
 (range 10000))

;;;;;;;;;;;;;;;;;

(def bar (atom 0))
(def call-counter (atom 0))
(import [java.lang Thread])

(defn slow-inc-with-call-count [x]
  (swap! call-counter inc)
  (Thread/sleep 100)
  (inc x))

(pmap
 (fn [_]
   (swap! bar slow-inc-with-call-count))
 (range 100))

@bar            ;; value 100
@call-counter   ;;called 1600+ times

;;agents/

(def my-agent
     (agent
      {:name "craig-andera"
       :favorites []}))

(defn slow-append-favorite
  [val new-fav]
  (Thread/sleep 2000)
  (assoc val
    :favorites
    (conj (:favorites val) new-fav)))

(do
  (send my-agent slow-append-favorite "food")
  (send my-agent slow-append-favorite "music")
  (println @my-agent)
  (Thread/sleep 2500)
  (println my-agent)
  (Thread/sleep 2500)
  (println @my-agent))

;;; agent errors

(def erroring-agent (agent 3))

(defn modify-agent-with-error
  "Modifies and agent with error on 42"
  [current new]
  (if (= 42 new)
    (throw (Exception. "NOT 42"))
    new))

(send erroring-agent
	    modify-agent-with-error 17)

(send erroring-agent
      modify-agent-with-error 42)

(agent-errors erroring-agent)

@erroring-agent

(clear-agent-errors erroring-agent)

;;; refs with STM mvcc    with -  commute/alter

(def foo (ref {:first "Craig" :last "Andrea" :children 2}))

(assoc @foo :blog "http://pluralsight.com/craig")

(dosync 
 (commute foo assoc :blog "http://pluralsight.com/craig"))

@foo

;;; macros

(defmacro with-new-thread [& body]
  `(.start (Thread. (fn [] ~@body))))

(macroexpand-1
 '(with-new-thread (print-foo)))

;;;;; refs

(def r (ref 0))

(with-new-thread
  (dosync
   (println "tx1 initial" @r)
   (alter r inc)
   (println "tx1 final" @r)
   (Thread/sleep 5000)
   (println "tx1 done")))

(with-new-thread
  (dosync
   (println "tx2 initial" @r)
   (Thread/sleep 1000)
   (alter r inc)
   (println "tx2 final" @r)
   (println "tx2 done")))



