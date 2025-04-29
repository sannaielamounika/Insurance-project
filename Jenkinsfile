pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "sannaielamounika/insure-me"
        DOCKER_TAG   = "latest"
    }

    stages {
        stage('Clone Repository') {
            steps {
                git 'https://github.com/sannaielamounika/Insurance-project.git'
            }
        }

        stage('Build Application') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Dockerize Application') {
            steps {
                sh """
                   docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} .
                   docker push ${DOCKER_IMAGE}:${DOCKER_TAG}
                """
            }
        }

        stage('Deploy to AWS') {
            steps {
                // Fetch the private key from Jenkins credentials (set up as "aws-ssh-key")
                withCredentials([file(credentialsId: 'ubuntu-ssh-key', variable: 'PRIVATE_KEY_PATH')]) {
                    sh """
                       ansible-playbook \
                         -i ansible/hosts.ini \
                         ansible/ansible-playbook.yml \
                         --private-key ${PRIVATE_KEY_PATH} \
                         -e 'ansible_ssh_extra_args="-o StrictHostKeyChecking=no"'
                    """
                }
            }
        }

        stage('Test Deployment') {
            steps {
                sh 'pip3 install selenium && python3 selenium-test.py'
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
