pipeline {
    agent any

    environment {
        MVN_HOME = tool 'M2_HOME' // Make sure 'Maven' is the name of the tool configured in Jenkins
        NODEJS_HOME = tool 'NODEJS_HOME' // Make sure 'NodeJS' is the name of the tool configured in Jenkins
        NEXUS_USER = 'admin'
        NEXUS_PASSWORD = 'admin'
        SNAP_REPO = 'devopsproject-snapshot'
        RELEASE_REPO = 'devopsproject-release'
        CENTRAL_REPO = 'devopsproject-central-repo'
        NEXUS_GRP_REPO = 'devopsproject-grp-repo'
        NEXUS_IP = 'localhost'
        NEXUS_PORT = '8081'
        NEXUS_LOGIN = 'b556b19d-561b-4293-be6d-53c092fff139'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        // stage('Unit Test') {
        //     steps {
        //         dir('DevOps_Project') {
        //             script {
        //                 sh "${MVN_HOME}/bin/mvn clean test"
        //             }
        //         }
        //     }
        // }

        stage('Build Backend') {
            steps {
                dir('DevOps_Project') {
                    script {
                        sh "${MVN_HOME}/bin/mvn clean package"
                    }
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }

        


           stage('SonarQube Analysis') {
    steps {
        script {
            // Checkout the source code from GitHub
            checkout scm
            
            def scannerHome = tool 'SonarQubeScanner'
            withSonarQubeEnv('SonarQube') {
                sh """
                    ${scannerHome}/bin/sonar-scanner \
                    -Dsonar.projectKey=HamzaProject \
                    -Dsonar.java.binaries=DevOps_Project/target/classes
                """
            }
        }
    }
}



//         stage('Build Docker Images') {
//     steps {
//         script {
//             // Build and push backend image
//             dir('DevOps_Project') {
//                 docker.build("hamzuss2000/hamzadevopsproject", "-f /var/lib/jenkins/workspace/HamzaBenAyedPipeline/DevOps_Project/Dockerfile .")
//             }

            
//         }
//     }
// // }
//        stage('Push image to Hub') {
//     steps {
//         script {
//             withCredentials([string(credentialsId: 'docker-hub-credentials-id', variable: 'DOCKER_HUB_PASSWORD')]) {
//                 dir('DevOps_Project') {
//                     sh "docker login -u hamzuss2000 -p ${DOCKER_HUB_PASSWORD}"
//                     sh "docker push hamzuss2000/hamzadevopsproject"
//                 }
//             }
//         }
//     }
// }

//         stage('Build and Deploy') {
//             steps {
//                 script {
//                     sh '/usr/bin/docker-compose -f /var/lib/jenkins/workspace/HamzaBenAyedPipeline/docker-compose.yml up -d'
//                 }
//             }
// }

       // stage('Deploy to Nexus') {
        //     steps {
        //         script {
        //             def artifactFile = "DevOps_Project/target/DevOps_Project-1.0.jar" // Replace with the actual artifact name pattern
        //             nexusArtifactUploader(
        //                 nexusVersion: 'nexus3',
        //                 protocol: 'http',
        //                 nexusUrl: "${NEXUS_IP}:${NEXUS_PORT}",
        //                 groupId: 'QA',
        //                 version: "${env.BUILD_ID}-${new Date().format('yyyyMMddHHmmss')}", // Correct timestamp format
        //                 repository: "${RELEASE_REPO}",
        //                 credentialsId: "${NEXUS_LOGIN}",
        //                 artifacts: [
        //                     [artifactId: 'DevOps_Project',
        //                      classifier: '',
        //                      file: artifactFile,
        //                      type: 'jar']
        //                 ]
        //             )
        //         }
        //     }
        // }
        
    }

    post {
        always {
            cleanWs()
        }
    }
}
