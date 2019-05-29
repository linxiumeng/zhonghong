#!/usr/bin/env bash

# user maven to build docker images and push result images onto harbor
/Users/nuoee/software/apache-maven-3.6.1/bin/mvn clean package docker:build
