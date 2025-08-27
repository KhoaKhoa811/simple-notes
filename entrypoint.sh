#!/bin/bash
set -e

JENKINS_URL=${JENKINS_URL:-http://jenkins-master:8080}
AGENT_JAR="$HOME/agent.jar"

# Retry download agent.jar cho đến khi thành công
until curl -fsSL "$JENKINS_URL/jnlpJars/agent.jar" -o "$AGENT_JAR"; do
    echo "Waiting for Jenkins master at $JENKINS_URL..."
    sleep 5
done

# Chạy agent
java -jar "$AGENT_JAR" \
    -jnlpUrl "$JENKINS_URL/computer/${AGENT_NAME}/jenkins-agent.jnlp" \
    -secret "$AGENT_SECRET" \
    -workDir "$HOME/agent"
