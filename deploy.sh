#!/bin/bash

# 변수
APP_NAME="goodeat"
JAR_NAME="$APP_NAME-0.0.1-SNAPSHOT.jar"
REMOTE_PATH="/home/ubuntu/server"

# 실행 중인 스프링 부트 앱 중지
sudo systemctl stop $APP_NAME || true

# Artifacts 디렉터리에서 EC2 인스턴스로 JAR 파일 복사
rsync -a ~/artifacts/$JAR_NAME $REMOTE_PATH

# 스프링 부트 앱 실행
nohup sudo systemctl start $APP_NAME > $REMOTE_PATH/nohup.out 2>&1 &
