pipeline {
   // agent { docker { image 'maven:3.3.3' } }
    //agent any
 /*   stages {
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
   */
   agent { docker { image 'node:7-alpine' } }
   stages {
      stage('Test') {
         environment {
            TEST_ACCOUNT=credentials('test_account');
         }
         steps {
             sh 'node --version'
             sh 'echo $TEST_ACCOUNT'
             sh 'echo $TEST_ACCOUNT_USR'
             sh 'echo $TEST_ACCOUNT_PSW'
             sh 'echo "hello world!"'
         }
      }
   }
   post {
      always {
         echo 'One way or another, I have finished'
         archiveArtifacts artifacts: 'target/**/*.war'
         junit 'build/reports/**/*.xml'
      }
      success {
          echo 'I succeeeded'
      }
      unstable {
          echo 'I am unstable :/'
      }
      failure {
         echo 'I failed :('
      }
      changed {
          echo 'Things were different before...'
      }
   }
}
