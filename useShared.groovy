@Library("shared-library") _
pipeline {
    agent any
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
        stage('Print Hello') {
            steps {
                script {
                    firstFunction()
                    twoFuncs.hello()
                    twoFuncs.bye()
                    printVariables.printParams()
                    printVariables.printEnv()
                    printVariables.jenkinsEnvVariables()
                }
            }
        }
    }
}

