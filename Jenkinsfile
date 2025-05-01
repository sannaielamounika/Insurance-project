pipeline {
  agent any

  environment {
    DOCKER_IMAGE = "sannaielamounika/insure-me"
    DOCKER_TAG   = "latest"
    APP_URL      = "http://ec2-52-4-82-198.compute-1.amazonaws.com:8085"
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

    stage('Wait for Application') {
      steps {
        echo "Waiting for application to be accessible at ${APP_URL} ..."
        script {
          def retries = 10
          def delay = 10 // seconds
          for (int i = 0; i < retries; i++) {
            def status = sh(script: "curl -s -o /dev/null -w '%{http_code}' ${APP_URL}", returnStdout: true).trim()
            if (status == '200') {
              echo "✅ Application is up!"
              break
            } else {
              echo "Attempt ${i + 1}/${retries}: Application not ready (status ${status})"
              sleep time: delay, unit: 'SECONDS'
            }
            if (i == retries - 1) {
              error("❌ Application did not become ready in time")
            }
          }
        }
      }
    }

    stage('Test Deployment') {
      steps {
        sh """
          docker run --rm \\
            --shm-size=1g \\
            -v "\$PWD":/workspace \\
            -w /workspace \\
            python:3.9-slim-buster bash -eux -c '
              apt-get update && \\
              apt-get install -y chromium chromium-driver && \\
              pip install selenium && \\
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
