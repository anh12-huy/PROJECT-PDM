
pipeline {
    agent any

    environment {
        PATH = "C:\\Users\\taman\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin;${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn -B -DskipTests clean package'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Test') {
            steps {
                bat 'mvn -B test'
            }
            post {
                always {
                    junit testResults: 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Docker Build') {
            steps {
                bat 'docker build -t employee-attendance:test .'
            }
        }

        stage('Deploy') {
            steps {
                bat 'docker rm -f employee-attendance-test 2>NUL || exit /b 0'

                withCredentials([usernamePassword(
                    credentialsId: 'employee-db',
                    usernameVariable: 'DB_USER',
                    passwordVariable: 'DB_PASSWORD'
                )]) {
                    bat '''
                    docker run -d --name employee-attendance-test -p 8081:8080 ^
                      -e "SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/employee_attendance?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC" ^
                      -e "SPRING_DATASOURCE_USERNAME=%DB_USER%" ^
                      -e "SPRING_DATASOURCE_PASSWORD=%DB_PASSWORD%" ^
                      employee-attendance:test
                    '''
                }
            }
        }

        stage('Health Check') {
            steps {
                bat '''
                powershell -NoProfile -Command "$ok = $false; for ($i = 0; $i -lt 20; $i++) { try { $r = Invoke-RestMethod -Uri 'http://localhost:8081/actuator/health' -TimeoutSec 3; if ($r.status -eq 'UP') { $ok = $true; break } } catch {} ; Start-Sleep -Seconds 3 }; if (-not $ok) { exit 1 }"
                '''
            }
        }
    }

    post {
        success {
            echo 'Build, tests, Docker deployment and health check succeeded.'
        }
        failure {
            echo 'Pipeline failed. Check the console output.'
        }
    }
}
