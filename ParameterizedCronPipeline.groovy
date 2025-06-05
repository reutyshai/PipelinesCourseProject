@Library("shared-library") _
pipeline {
    agent { label 'verisoft-2' }


    stages {
        stage('Functions') {
            steps {
                script {
                    properties([
                            pipelineTriggers([
                                    parameterizedCron([
                                            [cron: 'TZ=Asia/Jerusalem\n02 22 * * *', parameters: [string(name: 'ENV', value: 'dev')]],
                                            [cron: 'TZ=Asia/Jerusalem\n03 22 * * *', parameters: [string(name: 'ENV', value: 'prod')]]
                                    ])
                            ])
                    ])
                    echo "Start Time: ${currentBuild.startTimeInMillis}"
                }
            }
        }
    }
}



