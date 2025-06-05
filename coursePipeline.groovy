pipeline {
    agent {label 'verisoft-2'}

    parameters {
        string(name: 'NAME', defaultValue: 'Reut', description: 'Enter your name')
        booleanParam(name: 'DEBUG_MODE', defaultValue: false, description: 'Enable debug mode?')
        choice(name: 'ENVIRONMENT', choices: ['DEV', 'QA', 'PROD'], description: 'Choose environment')
        text(name: 'NOTES', defaultValue: 'No notes', description: 'Additional notes')
    }

    environment {
        STATIC_VALUE = "This is a static env var"
        COMBINED = "${params.NAME}-${params.ENVIRONMENT}"
    }

    stages {
        stage('Print Parameters') {
            steps {
                echo "Name: ${params.NAME}"
                echo "Debug Mode: ${params.DEBUG_MODE}"
                echo "Environment: ${params.ENVIRONMENT}"
                echo "Notes: ${params.NOTES}"
                echo "Static Env Var: ${env.STATIC_VALUE}"
                echo "Combined: ${env.COMBINED}"
                echo "Secret token won't be printed for security reasons"
            }
        }
    }

    post {
        always {
            echo 'Post: always – runs no matter what'
        }
        success {
            echo 'Post: success – runs only if build succeeded'
        }
        failure {
            echo 'Post: failure – runs only if build failed'
        }
        unstable {
            echo 'Post: unstable – runs if build is unstable'
        }
        changed {
            echo 'Post: changed – runs if build result changed from previous run'
        }
    }
}

