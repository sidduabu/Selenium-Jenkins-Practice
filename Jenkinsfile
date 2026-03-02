pipeline {
    agent any 
    parameters {
        choice(name: 'TEST_SUITE', choices: ['sanityTesting.xml', 'testng.xml'], description: 'Which test suite do you want to run?')
    }

    stages {
        stage('Checkout Code') {
            steps {
                // REPLACE THIS URL with your actual GitHub repository URL
                // Check if your default branch is 'main' or 'master' and update if needed
                git branch: 'main', url: 'https://github.com/sidduabu/Selenium-Jenkins-Practice.git'
            }
        }
        stage('Build and Test') {
            steps {
                sh "mvn clean test -DsuiteName=${params.TEST_SUITE}" 
            }
        } // <-- Closes 'stage'
    } // <-- Closes 'stages'
} // <--- YOU ARE MISSING THIS ONE (Closes 'pipeline')
