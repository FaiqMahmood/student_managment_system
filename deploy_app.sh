#!/bin/bash

# Define variables
APP_NAME="EduTrack"
JAR_FILE="target/$APP_NAME.jar"  # Adjust this path according to your project structure
REMOTE_USER="ec2-user"  # Replace with your remote SSH user
REMOTE_HOST="35.171.22.218"  # Replace with your EC2 instance IP address
REMOTE_DIR="/home/$REMOTE_USER/apps/$APP_NAME"  # Remote directory where the application will be deployed

# Install Git 
sudo yum install git -y

# Copy the JAR file to the remote server
scp -o StrictHostKeyChecking=no -i /dev/stdin "$JAR_FILE" "$REMOTE_USER@$REMOTE_HOST:$REMOTE_DIR/" << EOF
${{ secrets.SSH_PRIVATE_KEY }}
EOF

# Connect to the remote server and deploy the application
ssh -o StrictHostKeyChecking=no -i /dev/stdin "$REMOTE_USER@$REMOTE_HOST" << EOF
    # Install Java
    sudo yum install java-11-openjdk-devel -y  # Adjust the package name for your distribution if needed

    # Change directory to the application directory
    cd "$REMOTE_DIR"
    
    # Check if the application is already running and stop it
    pkill -f "$APP_NAME.jar" || true  # Kill the process if running, ignore errors
    
    # Start the application
    echo "Starting $APP_NAME..."
    nohup java -jar "$APP_NAME.jar" > "$APP_NAME.log" 2>&1 &
    disown
    echo "$APP_NAME deployed successfully."
EOF
