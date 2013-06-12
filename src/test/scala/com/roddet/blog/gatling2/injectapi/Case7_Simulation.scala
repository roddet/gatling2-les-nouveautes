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

class Case7_Simulation extends Simulation {

  val httpConf = httpConfig
    .acceptHeader("*/*")
    .acceptCharsetHeader("ISO-8859-1,utf-8;q=0.7,*;q=0.3")
    .acceptEncodingHeader("gzip,deflate,sdch")
    .acceptLanguageHeader("en-US,en;q=0.8")
    .connection("keep-alive")
    .userAgentHeader("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.22 (KHTML, like Gecko) Ubuntu/12.10 Chromium/25.0.1364.172 Chrome/25.0.1364.172 Safari/537.22")

  val scnSearchTwitter = scenario("Recherches sur Twitter")
    .exec(http("Recherche Twitter JCertif").get("http://search.twitter.com/search.json?q=jcertif"))
    .exec(http("Recherche Twitter Gatling").get("http://search.twitter.com/search.json?q=gatling"))
    .exec(http("Recherche Twitter Nantes").get("http://search.twitter.com/search.json?q=nantes"))
    .exec(http("Recherche Twitter Scala").get("http://search.twitter.com/search.json?q=scala"))

  val scnSearchFacebook = scenario("Recherches sur Facebook")
    .exec(http("Recherche Facebook JCertif").get("https://graph.facebook.com/search?q=jcertif&type=post"))
    .exec(http("Recherche Facebook Gatling").get("https://graph.facebook.com/search?q=gatling&type=post"))
    .exec(http("Recherche Facebook Nantes").get("https://graph.facebook.com/search?q=nantes&type=post"))
    .exec(http("Recherche Facebook Scala").get("https://graph.facebook.com/search?q=scala&type=post"))

  setUp(
    scnSearchTwitter.inject(atOnce(40 users)).protocolConfig(httpConf),
    scnSearchFacebook.inject(atOnce(40 users)) protocolConfig (httpConf))
}