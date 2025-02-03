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
                script {
                    if (isUnix()) {
                        // Ensure the mvnw script is executable
                        sh 'chmod +x mvnw'
                        sh './mvnw clean package -DskipTests=true'
                    } else {
                        // On Windows, call the Windows batch file
                        bat 'mvnw.cmd clean package -DskipTests=true'
                    }
                }
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'docker build -t enqms-app:latest .'
                    } else {
                        bat 'docker build -t enqms-app:latest .'
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying the application...'
                script {
                    if (isUnix()) {
                        sh 'docker run -d -p 9090:9090 enqms-app:latest'
                    } else {
                        bat 'docker run -d -p 9090:9090 enqms-app:latest'
                    }
                }
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
