pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from the Git repository
                git branch: 'main', url: 'https://github.com/FaiqMahmood/EduTrack.git'
            }
        }
        
        stage('Build') {
            steps {
                // Build the Java project
                sh 'mvn clean package'
            }
        }
        
        // stage('Test') {
        //     steps {
        //         // Run tests if any
        //         sh 'mvn test'
        //     }
        // }
        
        stage('Deploy') {
            steps {
                // Deploy the application
                sh './deploy_app.sh'
            }
        }
    }
    
    post {
        success {
            // If pipeline runs successfully, send notification
            echo 'Pipeline ran successfully!'
        }
        
        failure {
            // If pipeline fails, send notification
            echo 'Pipeline failed!'
        }
    }
}
