pipeline {
    agent any
    stages {
        stage('clone repo and clean') {
            steps {
                sh "mvn clean"
            }
        }
        stage('Test') {
            steps {
                sh "mvn test"
            }
        }
        stage('Deploy') {
            steps {
                sh "mvn package"
            }
        }
        post {
	        always {
		        junit(
			        allowEmptyResults:true,
			        testResults: '*test-reports/.xml'
			        )	
	        }

        }
    }
}
