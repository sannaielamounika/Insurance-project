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
          // Create and activate virtual environment
          sh 'python3 -m venv venv'
          
          // Fix permissions for the entire virtual environment folder
          sh 'chmod -R 755 venv'

          // Ensure the right pip version is installed inside the venv
          sh './venv/bin/python -m pip install --upgrade pip'

          // Install dependencies in the virtual environment
          sh './venv/bin/pip install selenium'
        }
      }
    }

    stage('Run Tests (Selenium)') {
      steps {
        script {
          // Run the Selenium tests using the virtual environment
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
