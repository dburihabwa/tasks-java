pipeline {
    agent {
        docker {
            image "maven:3.6.3-jdk-14"
            label "docker"
        }
    }

    stages {
        stage("Build") {
            steps {
                sh "mvn -version"
                sh "mvn clean test"
            }
        }
    }

    posts {
        always {
            cleanWs()
        }
    }
}
