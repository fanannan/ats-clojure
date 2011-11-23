(ns tws.client.twsclient
  (:import (com.ib.client EClientSocket Contract))
  (:require [wrapper.ewrapperimpl])
  ;"require should come before import for def record imports"
  (:import [wrapper.ewrapperimpl Ewrapperimpl])
  )

(def clientSocket (EClientSocket. (Ewrapperimpl.)))
(. clientSocket eConnect "localhost" 7496 3)
(def isConnected (. clientSocket isConnected))
(print "isConnected" isConnected)
(. clientSocket setServerLogLevel 5)
(. clientSocket reqAccountUpdates true "DU15237")
(. clientSocket reqCurrentTime)

(defn createContract
    ([securityName]
       (doto (Contract. )
          (:m_symbol securityName)
          (:m_secType "STK")
          (:m_exchange "SMART")
          (:m_expiry "")
          (:m_right "")
          (:m_multiplier "")
          (:m_primaryExch "")
          (:m_currency "USD")
          )
      )
      )

(def c (createContract "BAC"))
;(print c :m_symbol c :m_exchnage)
(. clientSocket reqHistoricalData 1  c  "20100507 12:00:00"  "3600 S"  "15 secs"  "TRADES"  0 1)


(defn disconnect
"Call this function to terminate the connections with TWS.
Calling this function does not cancel orders that have already been sent."
[connection]
(.eDisconnect connection))

(defn request-market-data
"Call this function to request market data. The market data will be returned by
:price-tick, :size-tick, :option-computation-tick, :generic-tick, :string-tick
and :efp-tick messages.

For snapshots, a :tick-snapshot-end message will indicate the snapshot is done.

## Parameters
- connection
The connection to use to make the request. Use (connect) to get this.

- tickerId
The ticker id. Must be a unique value. When the market data returns, it
will be identified by this tag. This is also used when canceling the
market data.

- contract
This contains attributes used to describe the contract. Use (make-contract) or
(futures-contract) for example to create it.

- tick-list (optional)
A list of tick types:
:option-volume Option Volume (currently for stocks)
:option-open-interest Option Open Interest (currently for stocks)
:historical-volatility 104 Historical Volatility (currently for stocks)
:option-implied-volatility 106 Option Implied Volatility (currently for stocks)
:index-future-premium 162 Index Future Premium
:miscellaneous-stats 165 Miscellaneous Stats
:mark-price 221 Mark Price (used in TWS P&L computations)
:auction-values 225 Auction values (volume, price and imbalance)
:realtime-volume 233 RTVolume
:shortable 236 Shortable
:inventory 256 Inventory
:fundamental-ratios 258 Fundamental Ratios
:realtime-historical-volatility 411 Realtime Historical Volatility

if no tick list is specified, a single snapshot of market data will come back
and have the market data subscription will be immediately canceled.
([connection id contract tick-list]
(let [ib-tick-list (map translate-to-ib-tick-type tick-list)]
(.reqMktData connection id contract ib-tick-list false))
id)
([connection id contract]
(.reqMktData connection id contract "" false)
id)"
)

(defn request-historical-data
"Start receiving historical price bars stretching back <duration> <duration-unit>s back,
up till <end> for the specified contract. The messages will have :request-id of <id>.
duration-unit should be one of :second(s), :day(s), :week(s), or :year(s).
bar-size-unit should be one of :second(s), :minute(s), :hour(s), or :day(s).
what-to-show should be one of :trades, :midpoint, :bid, :ask, :bid-ask, :historical-volatility,
:option-implied-volatility, :option-volume, or :option-open-interest."
([connection id contract end duration duration-unit bar-size bar-size-unit what-to-show use-regular-trading-hours?]
(let [ib-end (translate-to-ib-date-time end)
ib-duration (translate-to-ib-duration duration duration-unit)
ib-bar-size (translate-to-ib-bar-size bar-size bar-size-unit)
ib-what-to-show (translate-to-ib-what-to-show what-to-show)]
(.reqHistoricalData connection id contract ib-end ib-duration ib-bar-size ib-what-to-show
(if use-regular-trading-hours? 1 0)
2)))
([connection id contract end duration duration-unit bar-size bar-size-unit what-to-show]
(request-historical-data connection id contract end duration duration-unit bar-size bar-size-unit what-to-show true))
([connection id contract end duration duration-unit bar-size bar-size-unit]
(request-historical-data connection id contract end duration duration-unit bar-size bar-size-unit :trades true)))

(defn request-news-bulletins
"Call this function to start receiving news bulletins. Each bulletin will
be sent in a :news-bulletin, :exchange-unavailable, or :exchange-available
message."
([connection] (request-news-bulletins connection true))
([connection all-messages?]
(.reqNewsBulletins connection all-messages?)))

(defn cancel-news-bulletins
"Call this function to stop receiving news bulletins."
[connection]
(.cancelNewsBulletins connection))

(defn request-fundamental-data
"Call this function to receive Reuters global fundamental data. There must be a
subscription to Reuters Fundamental set up in Account Management before you
can receive this data."
[connection request-id contract report-type]
(.reqFundamentalData connection request-id contract
(translate-to-ib-report-type report-type)))

(defn cancel-fundamental-data
"Call this function to stop receiving Reuters global fundamental data."
[connection request-id]
(.cancelFundamentalData connection request-id))

(defn request-contract-details [connection request-id contract]
(.reqContractDetails connection request-id contract))

(defn is-end?
"Predicate to determine if a message indicates a tick snapshot is done"
[msg]
(contains? [:tick-snapshot-end :error]))

(defmulti warning? class)

(defmethod warning? java.lang.Integer [code]
(>= code 2100))

(defmethod warning? java.lang.Long [code]
(>= code 2100))

(defmethod warning? clojure.lang.IPersistentMap [{code :code exception :exception}]
(cond
(not (nil? exception)) false
(nil? code) false
:default (warning? code)))

(defmethod warning? :default [_]
false)

(def error? (comp not warning?))

