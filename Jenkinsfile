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
                    sh "docker login -u mashihimasha -p \${Dockerpwd}"
                }
            }
        }
        stage('Docker Push') {
            steps {
                sh "docker push mashihimasha/docker_jenkins_springboot:${BUILD_NUMBER}"
            }
        }
        stage('Kubernetes Deploy'){
            steps {
                withCredentials([string(credentialsId: 'AWS_CREDENTIALS', variable: 'AWS_CREDENTIALS')]) {
                    sh "aws configure set aws_access_key_id \${AWS_CREDENTIALS}"
                    sh "aws configure set aws_secret_access_key \${AWS_CREDENTIALS}"
                    sh "aws eks update-kubeconfig --name jenkins-devops-cluster --region eu-north-1"
                    sh 'kubectl apply -f k8s-spring-boot-deployment.yml'
                }
            }
        }
    }
}
