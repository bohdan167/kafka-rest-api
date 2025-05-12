#!/bin/bash

cd base-domains && ./mvnw -Dmaven.test.skip=true clean install && cd ../
cd calculator && ./mvnw -Dmaven.test.skip=true clean install dockerfile:build && cd ../
cd rest && ./mvnw -Dmaven.test.skip=true clean install dockerfile:build && cd ../
