pipeline {
    agent any

    stages {
        stage('Initialize Build') {
            steps {
                echo 'Starting the build process...'
                git branch: 'main', url: 'https://github.com/Ritish2-hash/6.2c.git'
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Executing unit and integration tests...'
            }
        }

        stage('Perform Code Review') {
            steps {
                echo 'Analyzing code quality...'
            }
        }

        stage('Security Assessment') {
            steps {
                echo 'Scanning for security vulnerabilities...'
            }
        }

        stage('Deploy to Test Environment') {
            steps {
                echo "Releasing the application to the staging server..."
            }
        }

        stage('Validate on Staging') {
            steps {
                echo "Executing validation tests on the staging setup..."
            }
        }

        stage('Release to Live System') {
            steps {
                 echo "Publishing the application to production..."
            }
        }
    }

    post {
        success {
            script {
                def powershellCommand = '''
                $SMTPServer = "smtp.gmail.com"
                $SMTPPort = 587
                $SMTPFrom = "ritish4803.be23@chitkara.edu.in"
                $SMTPTo = "ritish4803.be23@chitkara.edu.in"
                $SMTPSubject = "Pipeline Execution Success"
                $SMTPBody = "The Jenkins pipeline completed successfully."
                $SMTPUsername = "ritish4803.be23@chitkara.edu.in"
                $SMTPPassword = "zika urxx uaif wayj" 
                $SMTPEnableSSL = $true

                $SMTPClient = New-Object Net.Mail.SmtpClient($SMTPServer, $SMTPPort)
                $SMTPClient.EnableSsl = $SMTPEnableSSL
                $SMTPClient.Credentials = New-Object System.Net.NetworkCredential($SMTPUsername, $SMTPPassword)
                $SMTPClient.Send($SMTPFrom, $SMTPTo, $SMTPSubject, $SMTPBody)
                '''
                powershell(powershellCommand)
            }
            echo "Pipeline execution completed successfully!"
        }
    }
}
