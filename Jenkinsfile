pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                sh '''
                    docker compose down || true
                    docker compose up -d --build
                    docker ps
                '''
            }
        }

        stage('Health Check') {
            steps {
                sh '''
                    sleep 15
                    curl -f http://localhost:8085
                '''
            }
        }
    }
}
