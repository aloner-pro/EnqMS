pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/aloner-pro/EnqMS.git', branch: 'master'
            }
        }

        stage('Build') {
            steps {
                // Make mvnw executable before executing it
                sh 'chmod +x mvnw'
                // Build the Maven project and skip tests
                sh './mvnw clean package -DskipTests=true'
            }
        }

        stage('Docker Build') {
            steps {
                // Build Docker image
                sh 'docker build -t enqms-app:latest .'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying the application...'
                sh 'docker run -d -p 9090:9090 enqms-app:latest'
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully.'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}
