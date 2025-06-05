@Library("shared-library") _
pipeline {
    agent { label 'verisoft-2' }
    triggers {
        cron('TZ=Asia/Jerusalem\n42 21 * * *')
    }

    stages {
        stage('Functions') {
            steps {
                script {
                    echo "Start Time: ${currentBuild.startTimeInMillis}"
                }
            }
        }
    }
}


