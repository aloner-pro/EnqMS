pipeline {
    agent any
    environment {
        SCANNER_HOME = tool 'sonarscanner'
        DOCKERHUB_CREDENTIALS = credentials('docker-hub')
        APP_NAME = "devsohel/enqms-app"
        KUBECONFIG_CREDENTIALS_ID = 'kube-config'
    }
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/aloner-pro/EnqMS.git', branch: 'master'
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
        
        stage('Build') {
            steps {
                // Ensure the mvnw script is executable
                sh 'chmod +x mvnw'
                sh './mvnw clean package -DskipTests=true'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t $APP_NAME:$BUILD_NUMBER .'
            }
        }

        stage('Login to DockerHub') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }

        stage('Push Image') {
            steps {
                sh 'docker push $APP_NAME:$BUILD_NUMBER'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying the application...'
                withKubeConfig([credentialsId: KUBECONFIG_CREDENTIALS_ID]) {
                    sh 'kubectl apply -f app-deployment.yaml'
                    sh 'kubectl apply -f postgres-deployment.yaml'
                }
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
