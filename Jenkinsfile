pipeline {
    agent any
    environment {
        SCANNER_HOME = tool 'sonarscanner'
    }
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/aloner-pro/EnqMS.git', branch: 'master'
            }
        }

        stage('Build') {
            steps {
                // Ensure the mvnw script is executable
                sh 'chmod +x mvnw'
                sh './mvnw clean package -DskipTests=true'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarscanner') {
                    script {
                        withCredentials([string(credentialsId: 'sonar-cred', variable: 'SONAR_TOKEN')]) {
                            sh './mvnw org.sonarsource.scanner.maven:sonar-maven-plugin:4.0.0.4121:sonar -Dsonar.login=$SONAR_TOKEN -Dsonar.java.binaries=target/classes'
                        }
                    }
                }
            }
        }

        stage('Docker Build') {
            steps {
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
            mail to: 'terminateduser9@gmail.com',
                 subject: "✅ Build Succeeded: ${currentBuild.fullDisplayName}",
                 body: "The build has succeeded. Check details at ${env.BUILD_URL}"
        }
        failure {
            mail to: 'terminateduser9@gmail.com',
                 subject: "❌ Build Failed: ${currentBuild.fullDisplayName}",
                 body: "The build has failed. Check details at ${env.BUILD_URL}"
        }
    }
}
