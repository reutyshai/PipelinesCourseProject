pipeline {
    agent {label 'verisoft-2'}

    parameters {
        string(name: 'REPO_URL', defaultValue: 'https://github.com/reutyshai/AutomationFinalProject', description: 'Git repository URL')
    }

    environment {
        BRANCH = 'main'
    }

    stages {
        stage('Clone Repository') {
            steps {
                echo "Cloning ${params.REPO_URL} (branch: ${env.BRANCH})"
                git branch: "${env.BRANCH}",
                        url: "${params.REPO_URL}"

            }
        }

        stage('Build Project') {
            steps {
                echo "Running build..."
                sh 'mvn clean install'

            }
        }

        stage('Run Project') {
            steps {
                echo "Running application..."
                sh 'mvn test'
            }
        }
    }

    post {
        always {
            echo 'Cleaning workspace...'
            deleteDir()
        }
        success {
            echo 'Build & run completed successfully'
        }
        failure {
            echo 'Build or run failed'
        }
    }
}
