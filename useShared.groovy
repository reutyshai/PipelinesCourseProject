@Library("shared-library") _
pipeline {
    agent any

    stages {
        stage('Print Hello') {
            steps {
                script {
                    firstFunction()
                    twoFuncs.hello()
                    twoFuncs.bye()
                }
            }
        }
    }
}

