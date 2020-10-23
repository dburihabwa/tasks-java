pipeline {
    agent any

    tools {
        maven
        jdk
    }

    stages {
        stage("Build") {
            steps {
                sh "mvn clean build"
            }
        }
        stage("Test") {
            steps {
                sh "mvn test"
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
