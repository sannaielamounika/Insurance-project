pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "sannaielamounika/insure-me"
        DOCKER_TAG = "latest"
    }

    stages {
        stage('Clone Repository') {
            steps {
                git 'https://github.com/sannaielamounika/Insurance-project.git'
            }
        }
        stage('Build Application') {
            steps {
                script {
                    sh 'mvn clean install'
                }
            }
        }
        stage('Dockerize Application') {
            steps {
                script {
                    sh 'docker build -t $DOCKER_IMAGE:$DOCKER_TAG .'
                    sh 'docker push $DOCKER_IMAGE:$DOCKER_TAG'
                }
            }
        }
        stage('Deploy to AWS') {
            steps {
                script {
                    sh 'ansible-playbook -i ansible/hosts.ini ansible/ansible-playbook.yml -e "ansible_ssh_extra_args=\'-o StrictHostKeyChecking=no\'"'
                }
            }
        }
        stage('Test Deployment') {
            steps {
                script {
                    sh 'python3 selenium-test.py'
                }
            }
        }
    }

    post {
        success {
            echo "Deployment was successful"
        }
        failure {
            echo "Deployment failed"
        }
    }
}

