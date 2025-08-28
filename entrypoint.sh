#!/bin/bash
set -e

# Thay host:port bằng tên service của master trong docker-compose
JENKINS_URL=http://jenkins-master:8080

# Download agent.jar từ master nếu chưa có
if [ ! -f agent.jar ]; then
    echo "Downloading agent.jar from $JENKINS_URL..."
    curl -sSL $JENKINS_URL/jnlpJars/agent.jar -o agent.jar
fi

# Chạy agent
exec java -jar agent.jar \
    -url $JENKINS_URL \
    -secret $JENKINS_SECRET \
    -name $JENKINS_AGENT_NAME \
    -workDir /home/jenkins/agent \
    -webSocket
