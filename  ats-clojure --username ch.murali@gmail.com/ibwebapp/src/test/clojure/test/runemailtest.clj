(ns test.runemailtest
 
(:require [com.ats.email.email :as email])
 )
 

(println "sending email")
(email/mail :user  "ch.murali@gmail.com"
      :password "bhuvi2007"
      :host "smtp.gmail.com"
      :port "465"
      :ssl true
      :to ["ch.murali@gmail.com" ]
      :subject "I Have Rebooted." 
      :text "I Have Rebooted.")

(println "done")