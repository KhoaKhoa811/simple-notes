pipeline {
    agent {
        label 'docker' // Agent có Docker CLI + socket
    }

    environment {
        DOCKER_COMPOSE_FILE = 'docker-compose.yml'
        BRANCH = 'jenkins'
        SOME_ENV_VAR = 'some_value'
    }

    tools {
        jdk 'jdk-17'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: "${BRANCH}",
                    url: 'https://github.com/KhoaKhoa811/simple-notes.git'
            }
        }

        stage('Stop Old Containers') {
            steps {
                echo "Stopping old containers..."
                sh """
                    if [ -f \$DOCKER_COMPOSE_FILE ]; then
                        docker compose -f \$DOCKER_COMPOSE_FILE down || true
                    fi
                """
            }
        }

        stage('Build & Up Containers') {
            steps {
                echo "Building and starting containers..."
                sh """
                    docker compose -f \$DOCKER_COMPOSE_FILE up -d --build
                """
            }
        }

        stage('Post Build Info') {
            steps {
                echo "Listing running containers..."
                sh "docker ps"
            }
        }
    }

    post {
        success {
            echo "✅ Pipeline finished successfully."
        }
        failure {
            echo "❌ Pipeline failed. Check logs above."
        }
    }
}
