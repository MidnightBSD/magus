pipeline {
    agent any
    tools {
        maven 'maven'
        jdk 'jdk11'
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                    dropdb -h localhost -U postgres magus_test
                    psql -h localhost -c 'create database magus_test;' -U postgres
                    psql -h localhost -d magus_test -U postgres -p 5432 -a -q -f src/main/resources/db/migration/V1_0__magus_create.sql
                '''
            }
        }
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('jacoco') {
        steps {
        jacoco(
              execPattern: 'target/*.exec',
              classPattern: 'target/classes',
              sourcePattern: 'src/main/java',
              exclusionPattern: 'src/test*'
        )
        }
        }
        stage('SonarQube') {
            steps {

        	   env.sonarHome= tool name: 'sonar-scanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'

                 withSonarQubeEnv('sonar.installation') { // from SonarQube servers > name
                   sh "${sonarHome}/bin/sonar-runner -Dsonar.host.url=${SONAR_HOST_URL}  -Dsonar.login=${SONAR_AUTH_TOKEN}  -Dsonar.projectName=magus -Dsonar.sources=."

                 }
            }
        }
    }
}
