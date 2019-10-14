#!/bin/bash

wireMockServerPort=8091

wget -c -t 10 "http://repo1.maven.org/maven2/com/github/tomakehurst/wiremock-standalone/2.25.0/wiremock-standalone-2.25.0.jar"
java -jar wiremock-standalone-2.25.0.jar --port $wireMockServerPort --verbose &

# stub data to wiremock server
sleep 3
bash stubData.sh $wireMockServerPort
