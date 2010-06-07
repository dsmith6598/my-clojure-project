(defproject leiningen "0.5.0-SNAPSHOT"
  :description "A build tool designed not to set your hair on fire."
  :url "http://github.com/technomancy/leiningen"
  :dependencies [[org.clojure/clojure "1.2.0-master-SNAPSHOT"]
                 [org.clojure/clojure-contrib "1.2.0-SNAPSHOT"]
                 [ant/ant-launcher "1.6.2"]
                 [org.apache.maven/maven-ant-tasks "2.0.10"]
                 [compojure "0.4.0-RC3"]
		 [fleetdb "0.2.0-RC1"]
		 [fleetdb-client "0.2.0-RC1"]
		 [ring/ring "0.2.0"]
       	       	[ring/ring-core "0.2.0"]]
                   
                  
   :dev-dependencies [[swank-clojure "1.2.1"]])