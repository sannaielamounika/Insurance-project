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
                // Uses "Ubuntu-ssh-key" credential of type SSH Username with private key
                sshagent(['ubuntu-ssh-key']) {
                    sh """
                       ansible-playbook \
                         -i ansible/hosts.ini \
                         ansible/ansible-playbook.yml \
                         -e 'ansible_ssh_extra_args="-o StrictHostKeyChecking=no"'
                    """
                }
            }
        }

        stage('Test Deployment') {
            steps {
                script {
                    // Ensure pip3 is installed on the agent
                    sh '''
                      if ! command -v pip3 >/dev/null 2>&1; then
                        sudo apt-get update -y
                        sudo apt-get install -y python3-pip
                      fi
                    '''
                    // Install Selenium and run the test
                    sh '''
                      pip3 install selenium
                      python3 selenium-test.py
                    '''
                }
            }
        }
    }

    post {
        success {
            echo "✅ Deployment was successful"
        }
        failure {
            echo "❌ Deployment failed"
        }
    }
}
