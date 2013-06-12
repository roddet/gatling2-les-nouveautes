package com.roddet.blog.gatling2.injectapi

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import io.gatling.http.Headers.Names._
import io.gatling.http.Headers.Values._
import io.gatling.http.request.RequestBodyProcessors._
import scala.concurrent.duration._
import io.gatling.core.Predef.bootstrap._
import io.gatling.core.Predef.assertions._

class Case4_Simulation extends Simulation {

  val httpConf = httpConfig
    .baseURL("http://search.twitter.com")
    .acceptHeader("*/*")
    .acceptCharsetHeader("ISO-8859-1,utf-8;q=0.7,*;q=0.3")
    .acceptEncodingHeader("gzip,deflate,sdch")
    .acceptLanguageHeader("en-US,en;q=0.8")
    .connection("keep-alive")
    .userAgentHeader("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.22 (KHTML, like Gecko) Ubuntu/12.10 Chromium/25.0.1364.172 Chrome/25.0.1364.172 Safari/537.22")

  val scn = scenario("Recherches sur Twitter")
    .exec(http("Recherche JCertif").get("/search.json?q=jcertif"))
    .exec(http("Recherche Gatling").get("/search.json?q=gatling"))
    .exec(http("Recherche Nantes").get("/search.json?q=nantes"))
    .exec(http("Recherche Scala").get("/search.json?q=scala"))

  setUp(scn.inject(
	constantRate(2 usersPerSec) during (2 minutes)
	).protocolConfig(httpConf))
}