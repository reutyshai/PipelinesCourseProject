@Library("shared-library") _
pipeline {
    agent { label 'verisoft-2' }

    parameters {
        string(name: 'NAME', defaultValue: 'Reut', description: 'Enter your name')
        booleanParam(name: 'DEBUG_MODE', defaultValue: false, description: 'Enable debug mode?')
        choice(name: 'ENVIRONMENT', choices: ['DEV', 'QA', 'PROD'], description: 'Choose environment')
    }
    stages {
        stage('Functions') {
            steps {
                script {
                    properties([
                            pipelineTriggers([
                                    parameterizedCron('''
                                        TZ=Asia/Jerusalem
                                        07 22 * * *%NAME=Gila;DEBUG_MODE=true
                                        08 22 * * *%ENVIRONMENT=PROD;DEBUG_MODE=true'''
                                    )
                            ])
                    ])
                    echo "params: ${params}"
                }
            }
        }
    }
}



