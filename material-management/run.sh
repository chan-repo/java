#!/bin/bash

echo "================================"
echo "자재 관리 시스템 시작"
echo "================================"

# Maven이 설치되어 있는지 확인
if ! command -v mvn &> /dev/null
then
    echo "Maven이 설치되어 있지 않습니다."
    echo "Maven을 먼저 설치해주세요: https://maven.apache.org/"
    exit 1
fi

# 빌드 확인
if [ ! -f "target/material-management-swing-1.0.0.jar" ]; then
    echo "프로젝트 빌드 중..."
    mvn clean package -DskipTests
    if [ $? -ne 0 ]; then
        echo "빌드 실패!"
        exit 1
    fi
fi

# 실행
echo "애플리케이션 실행 중..."
java -jar target/material-management-swing-1.0.0.jar

echo ""
echo "애플리케이션이 종료되었습니다."
