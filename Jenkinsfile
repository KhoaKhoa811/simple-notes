pipeline {
    agent {
        label 'jenkins-master' // Agent phải có Docker CLI + socket
    }

    environment {
        DOCKER_COMPOSE_FILE = 'docker-compose.yml'
        BRANCH = 'jenkins'
        SOME_ENV_VAR = 'some_value'
    }

    tools {
        docker 'latest'   // Docker CLI đã cấu hình trong Jenkins
        jdk '21'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: "${BRANCH}",
                    url: 'https://github.com/KhoaKhoa811/simple-notes.git'
            }
        }

        stage('Stop & Remove Old Containers') {
            steps {
                echo "Stopping old containers..."
                sh """
                    if [ -f \$DOCKER_COMPOSE_FILE ]; then
                        docker compose -f \$DOCKER_COMPOSE_FILE down
                    fi
                """
            }
        }

        stage('Build Docker Image') {
            steps {
                echo "Building Docker image..."
                sh "docker build . -t simple-notes-app:latest"
            }
        }

        stage('Build & Up Containers') {
            steps {
                echo "Starting containers with Docker Compose..."
                sh """
                    docker compose -f \$DOCKER_COMPOSE_FILE build --no-cache
                    docker compose -f \$DOCKER_COMPOSE_FILE up -d
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
            echo "Pipeline finished successfully."
        }
        failure {
            echo "Pipeline failed. Check logs above."
        }
    }
}
