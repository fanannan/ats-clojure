(ns wrapper.ewrapperimpl
    (:import (com.ib.client EWrapper))
    (:import (java.lang Exception))
    (:use [clojure.tools.logging :only (info error)])
)

(defrecord Ewrapperimpl[]
  EWrapper
  (#^void error [this #^String e]
    (error e)
    )
  (#^void error [this #^Exception e]
    (error e)
    )
  (error [this i i1 string]
    (info i i1 string)
    )
  (#^void connectionClosed[this]
    (info "connectionClosed")
    )
  (tickPrice[this i i1 d i2]
    (info i i1 d i2)
    )
  (tickSize[this,  i, i1, i2])
  (tickOptionComputation[this,  i,i1,d,d1,d2,d3,d4,d5,d6,d7])
  (tickGeneric[this,  i,i1,d])
  (tickString[this,  i,i1,string])
  (tickEFP[this,  i,i1,d,string,d1,i2,string1,d2,d3])
  (orderStatus[this,  i,string,i1,i2,d,i3,i4,d1,i5,string1])
  (openOrder[this,  i,cntrct,order,os])
  (openOrderEnd[this,  ])
  (updateAccountValue[this,  string,  string1,  string2,  string3])
  (updatePortfolio[this,  cntrct,i,d,d1,d2,d3,d4,  string])
  (updateAccountTime[this,  string])
  (accountDownloadEnd[this,   string])
  (nextValidId[this,  i])
  (contractDetails[this,i,cd])
  (bondContractDetails[this,   i,cd])
  (contractDetailsEnd[this,   i])
  (execDetails[this,   i, cntrct,  exctn])
  (execDetailsEnd[this,   i])
  (updateMktDepth[this,   i,  i1,  i2,  i3,  d,i4])
  (updateMktDepthL2[this,   i,  i1,  string,  i2,  i3,  d,  i4])
  (updateNewsBulletin[this,  i,  i1,  string,  string1])
  (managedAccounts[this,   string])
  (receiveFA[this,   i,  string])
  (historicalData[this requestId date open high low close volume count wap hasGaps]
    (info "historical data:" requestId date open high low close volume count wap hasGaps )
    
    )
  (scannerParameters[this,   string])
  (scannerData[this,   i,  i1,cd,  string,  string1,  string2,  string3])
  (scannerDataEnd[this,   i])
  (realtimeBar[this,i,l,d,d1,d2,d3,l1,d4,i1]
    (info this i l d d1 d2 d3 l1 d4 i1)
    )
  (currentTime[this,l]
    (info "currenttime on tws" l)
    )
  (fundamentalData[this,   i,  string])
  (deltaNeutralValidation[this,   i,uc])
  (tickSnapshotEnd[this,   i])

  )
