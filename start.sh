#!/bin/bash
set -e
su postgres -c "pg_ctl -D /var/lib/postgresql/data -o \"-c listen_addresses='*'\" -w start"
sleep 3
mvn -f /app/pom.xml -DskipTests package
exec java -jar /app/target/*.jar
