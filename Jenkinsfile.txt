pipeline {
    agent any
    
    tools{
        
        maven "INSTALLED_MAVEN"
    }
   

    stages {
        stage('Checkout') {
            steps {
               git branch: 'main', credentialsId: 'git_credentials', url: 'https://github.com/udayappdeveloper/MacysMessageProducer.git'
           
            }

        }
        
        stage('Build'){
            
            steps{
                
                bat 'mvn clean package'
            }
        }
       
    stage('Test'){
            
            steps{
                
                bat 'mvn test'
            }
        }
     
    }
}
