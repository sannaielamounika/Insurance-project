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
        // Run Selenium test in a Python container with Chrome & chromedriver
        sh """
          docker run --rm \\
            -v "\$PWD":/workspace \\
            -w /workspace \\
            python:3.9-slim-buster bash -eux -c '
              apt-get update && \
              apt-get install -y chromium-driver chromium wget gnupg && \
              pip install selenium && \
              python3 selenium-test.py
            '
        """
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
