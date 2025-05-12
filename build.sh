#!/bin/bash

cd base-domains && ./mvnw package -DskipTests -Dmaven.javadoc.skip=true -Dcheckstyle.skip=true && cd ../
cd rest && ./mvnw package -DskipTests -Dmaven.javadoc.skip=true -Dcheckstyle.skip=true && cd ../
cd calculator && ./mvnw package -DskipTests -Dmaven.javadoc.skip=true -Dcheckstyle.skip=true && cd ../
