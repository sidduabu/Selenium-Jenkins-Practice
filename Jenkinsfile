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
                // 2. THIS PASSES YOUR SELECTION FROM JENKINS TO MAVEN
                // Notice we changed single quotes ('') to double quotes ("") so Jenkins reads the variable!
                sh "mvn clean test -DsuiteName=${params.TEST_SUITE}" 
            }

    /*    stage('Run Selenium Tests') {
            steps {
                // If you are on Windows, use 'bat'. If on Mac/Linux, change this to 'sh'
                sh 'mvn clean test' 
            }
        }*/
    }
}
