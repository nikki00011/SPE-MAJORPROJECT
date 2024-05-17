pipeline {
    // environment{
    //     DOCKERHUB_CREDENTIALS=credentials('docker-jenkins')
    // }
    agent any

    stages {
        stage('Git Pull') {
            steps {
                // git credentialsId: 'e261986b-ee1d-4dda-bb39-a1e2cd880ebf', url: 'https://github.com/nikki00011/SPE-MAJORPROJECT.git\'
		    checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'e261986b-ee1d-4dda-bb39-a1e2cd880ebf', url: 'https://github.com/nikki00011/SPE-MAJORPROJECT.git']])
            }
        }
        stage('Maven Build') {
            steps {
                dir('./Backend') {
                    sh 'mvn clean install'
                }
            }
        }
 //        stage('Build Docker Images') {
 //            steps {
 //                dir('./backend') {
 //                    sh 'docker build -t venkateshkvrs/ebill-backend .'
 //                }
 //                dir('./frontend') {
 //                    sh 'docker build -t venkateshkvrs/ebill-frontend .'
 //                }
 //            }
 //        }
 //        stage('Dockerhub Login') {
 //            steps {
	// 			sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
	// 		}
	// 	}
 //        stage('Push Docker Images') {
 //            steps {
 //                script{
 //                    sh 'docker push venkateshkvrs/ebill-backend'
 //                    sh 'docker push venkateshkvrs/ebill-frontend'
 //                }
 //            }
 //        }
 //        stage('Ansible Pull & Deploy') {
 //            steps {
 //               ansiblePlaybook colorized: true, disableHostKeyChecking: true, installation: 'Ansible', inventory: 'deploy-docker/inventory', playbook: 'deploy-docker/ebill-deploy.yml'
 //            }
 //        }

 //    }
 //    post {
	// 	always {
	// 		sh 'docker logout'
	// 	}
	// }
}
