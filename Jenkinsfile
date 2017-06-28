pipeline {
    agent any
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

	  stage('Release build') {
            when { branch 'master' }
            agent any
            steps {
                script {
                    currentBuild.description = "Release: ${env.version}"
                    sh "mvn versions:set -DnewVersion=${env.version}"
                    sh 'mvn clean deploy -B'
                    step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
                    tagPuppetModules("${env.version}")
                }
            }
        }
	}
		}