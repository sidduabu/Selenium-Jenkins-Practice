pipeline {
    agent any 

    stages {
        stage('Checkout Code') {
            steps {
                // REPLACE THIS URL with your actual GitHub repository URL
                // Check if your default branch is 'main' or 'master' and update if needed
                git branch: 'main', url: 'https://github.com/sidduabu/Selenium-Jenkins-Practice.git'
            }
        }

        stage('Run Selenium Tests') {
            steps {
                // If you are on Windows, use 'bat'. If on Mac/Linux, change this to 'sh'
                sh 'mvn clean test' 
            }
        }
    }
}