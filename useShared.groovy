@Library("shared-library") _
pipeline {
    agent any

    stages {
        stage('Print Hello') {
            steps {
                firstFunction()
                twoFuncs.hello()
                twoFuncs.bye()
            }
        }
    }
}

