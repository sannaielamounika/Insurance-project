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

    stage('Run Tests (Selenium)') {
      steps {
        sh 'python3 selenium/test_script.py'
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
