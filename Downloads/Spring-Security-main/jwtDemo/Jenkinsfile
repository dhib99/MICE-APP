pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo '🛠️ Building the project...'
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                echo '✅ Running tests...'
                sh 'mvn test'
            }
        }
        stage('Package') {
            steps {
                echo '📦 Packaging the app...'
                sh 'mvn package'
            }
        }
    }
}
