pipeline {
    agent any
    stages {
        stage('Compile and Clean') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('Deploy') {
            steps {
                sh 'mvn package'
            }
        }
        stage('Build Docker image') {
            steps {
                echo "Hello Java Express"
                sh 'ls'
                sh 'docker build -t mashihimasha/docker_jenkins_springboot:${BUILD_NUMBER} .'
            }
        }
        stage('Docker Login') {
            steps {
                withCredentials([string(credentialsId: 'mashihimasha', variable: 'Dockerpwd')]) {
                    sh "docker login -u mashihimasha -p ${Dockerpwd}"
                }
            }
        }
        stage('Docker Push') {
            steps {
                sh 'docker push mashihimasha/docker_jenkins_springboot:${BUILD_NUMBER}'
            }
        }
        stage('Docker Deploy') {
            steps {
                sh 'docker run -itd -p 8081:8080 mashihimasha/docker_jenkins_springboot:${BUILD_NUMBER}'
            }
        }
        stage('Kubernetes Deploy'){
            steps{
                sh 'kubectl apply -f k8s-spring-boot-deployment.yml'
            }
        }
    }
}
