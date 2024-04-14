#!/bin/bash

# Define variables
APP_NAME="EduTrack"
JAR_FILE="target/$APP_NAME.jar"  # Adjust this path according to your project structure
REMOTE_USER="ec2-user"  # Replace with your remote SSH user
REMOTE_HOST="35.171.22.218"  # Replace with your EC2 instance IP address
REMOTE_DIR="/home/$REMOTE_USER/apps/$APP_NAME"  # Remote directory where the application will be deployed

# Copy the JAR file to the remote server
scp -o StrictHostKeyChecking=no -i /path/to/your/private/key "$JAR_FILE" "$REMOTE_USER@$REMOTE_HOST:$REMOTE_DIR/"

# Connect to the remote server and restart the application
ssh -o StrictHostKeyChecking=no -i /path/to/your/private/key "$REMOTE_USER@$REMOTE_HOST" << EOF
    # Install Java if not already installed
    if ! command -v java &> /dev/null; then
        sudo yum install java-11-openjdk-devel -y  # Adjust the package name for your distribution if needed
    fi

    # Change directory to the application directory
    cd "$REMOTE_DIR"
    
    # Check if the application is already running
    if pgrep -f "$APP_NAME.jar" > /dev/null; then
        echo "Stopping $APP_NAME..."
        pkill -f "$APP_NAME.jar"
        sleep 5
    fi

    # Start the application
    echo "Starting $APP_NAME..."
    nohup java -jar "$APP_NAME.jar" > "$APP_NAME.log" 2>&1 &
    disown
    echo "$APP_NAME deployed successfully."
EOF
