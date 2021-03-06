(ns ayman-httpkit.routes
    (:use [compojure.core :only [defroutes GET POST DELETE ANY context]]
          (ring.middleware [keyword-params :only [wrap-keyword-params]]
                           [params :only [wrap-params]]
                           [session :only [wrap-session]])
          [ayman-httpkit.middleware :only [wrap-request-logging-in-dev
                                           wrap-failsafe]])
    (:require [compojure.route :as route]
              [org.httpkit.server :refer :all]
              [clojure.core.async :refer [go <! timeout]]))

(defn asyncit [req]
  (with-channel req chan
    (go
      (<! (timeout 3000))
      (send! chan "this works"))))

;; define mapping here
(defroutes server-routes*
  (GET "/" [] "hello world")
  (GET "/load" req (asyncit req))
  ;; static files under ./public folder, prefix /static
  ;; like /static/css/style.css
  (route/files "/static")
  ;; 404, modify for a better 404 page
  (route/not-found "<p>Page not found.</p>" ))

(defn app [] (-> #'server-routes*
                 wrap-session
                 wrap-keyword-params
                 wrap-params
                 wrap-request-logging-in-dev))
