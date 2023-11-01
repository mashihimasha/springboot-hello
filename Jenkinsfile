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
                sh 'docker build -t mashihimasha/docker_jenkins_springboot:latest_2 .'
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
                sh "docker push mashihimasha/docker_jenkins_springboot:latest_2"
            }
        }
        stage('Kubernetes Deploy'){
            steps {
                withCredentials([string(credentialsId: 'AWS_CREDENTIALS', variable: 'AWS_CREDENTIALS')]) {
                    sh "echo \${AWS_CREDENTIALS} > aws-credentials.json"
                    sh "export PATH=\$PATH:/path/to/jq"  // Add the path to 'jq' executable
                    sh "aws configure set aws_access_key_id \$(jq -r .accessKey aws-credentials.json)"
                    sh "aws configure set aws_secret_access_key \$(jq -r .secretKey aws-credentials.json)"
                    sh "aws eks update-kubeconfig --name jenkins-devops-cluster --region eu-north-1"
                    sh 'kubectl apply -f k8s-spring-boot-deployment.yml'
                }
            }
        }

    }
}
