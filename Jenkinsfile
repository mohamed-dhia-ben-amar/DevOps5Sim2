pipeline {
    agent any

    environment {
        MVN_HOME = tool 'M2_HOME' // Make sure 'Maven' is the name of the tool configured in Jenkins
        NODEJS_HOME = tool 'NODEJS_HOME' // Make sure 'NodeJS' is the name of the tool configured in Jenkins
        NEXUS_USER = 'admin'
        NEXUS_PASSWORD = 'admin'
        SNAP_REPO = 'Mondher_Nexus_Repo-snapshot'
        RELEASE_REPO = 'Mondher_Nexus_Repo-lastreelase'
        CENTRAL_REPO = 'Mondher_Nexus_Repo-central-repo'
        NEXUS_GRP_REPO = 'Mondher_Nexus_Repo-grp-repo'
        NEXUS_IP = 'localhost'
        NEXUS_PORT = '8081'
        NEXUS_LOGIN = '485b967a-8f70-498c-8c77-b47207262286'
        // Dynamically assign 'version' or ensure it matches the version in pom.xml
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
            
            def scannerHome = tool 'SonarQube'
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

        // stage('Deploy to Nexus') {
        //     steps {
        //         script {
        //             def artifactFile = "DevOps_Backend/target/DevOps_Project-1.0.jar" // Replace with the actual artifact name pattern
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
