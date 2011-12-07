#mvn install:install-file -DgroupId=com.ib -DartifactId=jtsclient -Dversion=9.6 -Dpackaging=jar -Dfile=lib/jtsclient-9.6.jar
#mvn install


javaws /home/mchapala/Downloads/tws.jnlp &
#edemo/demouser
mvn clean clojure:repl
