#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'
scp -P 4586 -i ~/.ssh/id_rsa \
    target/spring-boot-0.0.1-SNAPSHOT.jar \
    lunk@194.35.119.103:/home/lunk/
echo 'Restart server...'

ssh -p 4586 -i ~/.ssh/id_rsa lunk@194.35.119.103 << EOF
pgrep java | xargs kill -9
nohub java -jar spring-boot-0.0.1-SNAPSHOT.jar > log.txt &

EOF

echo 'Bye'