pipeline {
   // agent { docker { image 'maven:3.3.3' } }
    agent any
    stages {
        stage('build') {
            steps {
                sh 'mvn --version'
                sh 'echo "Hello World"'
                sh '''
                    echo "Multiline sheel steps "
                    ls -lah
                '''
            }
        }
    }
}
