@Library("shared-library") _
pipeline {
    agent { label 'verisoft-2' }
    properties([
            pipelineTriggers([
                    parameterizedCron([
                            [cron: 'TZ=Asia/Jerusalem\n02 22 * * *', parameters: [string(name: 'ENV', value: 'dev')]],
                            [cron: 'TZ=Asia/Jerusalem\n03 22 * * *', parameters: [string(name: 'ENV', value: 'prod')]]
                    ])
            ])
    ])

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



