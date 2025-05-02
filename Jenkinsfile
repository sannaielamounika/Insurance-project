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

    stage('Set up Python Environment') {
      steps {
        script {
          // Remove the old virtual environment if it exists
          sh 'rm -rf venv'

          // Create a new virtual environment
          sh 'python3 -m venv venv'

          // Make the virtual environment executable
          sh 'chmod -R 755 venv'

          // Install pip in the virtual environment and upgrade it
          sh './venv/bin/python -m ensurepip --upgrade'

          // Install Selenium in the virtual environment
          sh './venv/bin/pip install selenium'

          // Install Chromium and ChromeDriver
          sh """
            sudo apt-get update
            sudo apt-get install -y chromium-browser
            sudo apt-get install -y chromium-chromedriver
          """
        }
      }
    }

    stage('Run Tests (Selenium)') {
      steps {
        script {
          // Set the path for the Chromium browser explicitly
          sh './venv/bin/python selenium-test.py'
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
