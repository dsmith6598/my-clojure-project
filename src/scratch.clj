



("hello, world!")

;;; M-; with paredit


(def x 5)

(def lst '(a b c))
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
     (recur (conj result x) (dec x))))

					
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





