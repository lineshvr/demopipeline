pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                bat 'set'
            }
        }
    }
    stages {
        stage('Branch build') {
            agent any
            steps {
                script {
                    env.commitId = readCommitId()
                    env.commitMessage = readCommitMessage()
                    env.version = DateTimeFormatter.ofPattern('yyyy-MM-dd-HHmm').format(now(ZoneId.of('UTC'))) + "-" + env.commitId.take(6)
                    if (isQuickBuild()) {
                        currentBuild.description = "Building: ${env.commitId}"
                        sh 'mvn clean verify'
                    }
                }
            }
        }
	}