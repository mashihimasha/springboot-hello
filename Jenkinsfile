pipeline {
    agent any
    environment {
        KUBECONFIG = '/home/ec2-user/.kube/config'
    }
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
        stage('Authenticate to Kubernetes') {
            steps {
                sh 'kubectl config use-context arn:aws:eks:eu-north-1:834905904425:cluster/jenkins-devops-cluster'
            }
        }
        stage('Kubernetes Deploy'){
            steps{
                sh 'kubectl apply -f k8s-spring-boot-deployment.yml'
            }
        }
    }
}
