pipeline {
    agent any

    parameters {
        string(name: 'REPO_URL', defaultValue: 'https://github.com/reutyshai/AutomationFinalProject', description: 'Git repository URL')
        string(name: 'BRANCH', defaultValue: 'main', description: 'Branch to clone')
    }

    environment {
        PROJECT_DIR = 'downloaded-project'
    }

    stages {
        stage('Clone Repository') {
            steps {
                echo "Cloning ${params.REPO_URL} (branch: ${params.BRANCH})"

                dir(env.PROJECT_DIR) {
                    git branch: "${params.BRANCH}",
                        url: "${params.REPO_URL}"
                }
            }
        }

        stage('Build Project') {
            steps {
                dir(env.PROJECT_DIR) {
                    echo "Running build..."
                    sh 'mvn clean install'
                }
            }
        }

        stage('Run Project') {
            steps {
                dir(env.PROJECT_DIR) {
                    echo "Running application..."
                    sh 'mvn test'
                }
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
