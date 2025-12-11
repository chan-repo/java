@echo off
echo ================================
echo 자재 관리 시스템 시작
echo ================================

REM Maven 설치 확인
where mvn >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo Maven이 설치되어 있지 않습니다.
    echo Maven을 먼저 설치해주세요: https://maven.apache.org/
    pause
    exit /b 1
)

REM 빌드 확인
if not exist "target\material-management-swing-1.0.0.jar" (
    echo 프로젝트 빌드 중...
    call mvn clean package -DskipTests
    if %ERRORLEVEL% NEQ 0 (
        echo 빌드 실패!
        pause
        exit /b 1
    )
)

REM 실행
echo 애플리케이션 실행 중...
java -jar target\material-management-swing-1.0.0.jar

echo.
echo 애플리케이션이 종료되었습니다.
pause
