# 자재 관리 시스템 (Material Management System)

## 프로젝트 개요
Java Swing 기반의 데스크톱 자재 관리 시스템입니다.
MySQL 데이터베이스를 사용하여 자재 정보, 재고 현황, 입출고 이력을 관리합니다.

## 기술 스택
- **언어**: Java 17
- **UI 프레임워크**: Swing (FlatLaf 테마 적용)
- **데이터베이스**: MySQL 8.0
- **빌드 도구**: Maven
- **주요 라이브러리**:
  - FlatLaf 3.2.5 (모던 Look and Feel)
  - MySQL Connector 8.2.0
  - jBCrypt 0.4 (비밀번호 암호화)
  - Lombok 1.18.30

## 주요 기능

### 1. 사용자 인증
- 로그인/로그아웃
- 권한 관리 (관리자/사용자)
- BCrypt 비밀번호 암호화

### 2. 대시보드
- 총 자재 수 통계
- 재고 부족 자재 알림
- 최근 입출고 거래 내역
- 실시간 현황 모니터링

### 3. 자재 관리
- 자재 등록/수정/삭제
- 자재 정보 조회 및 검색
- 카테고리별 관리
- 최소 재고 설정

### 4. 입출고 관리
- 입고 처리
- 출고 처리
- 재고 조정
- 입출고 이력 조회 및 필터링

### 5. 사용자 관리 (관리자 전용)
- 사용자 계정 추가/수정
- 권한 설정 (관리자/사용자)
- 비밀번호 변경
- 계정 활성화/비활성화

### 5. 사용자 관리 (관리자 전용)
- 사용자 계정 추가/수정/삭제
- 권한 설정 (관리자/사용자)
- 비밀번호 변경
- 사용자 활성화/비활성화
- 사용자 검색

## 사전 요구사항
- JDK 17 이상
- Maven 3.6 이상
- MySQL 8.0 이상

## 데이터베이스 설정

### 1. MySQL 설치 및 실행
```bash
# MySQL 서버가 실행 중인지 확인
sudo systemctl status mysql
```

### 2. 데이터베이스 설정
애플리케이션 실행 시 자동으로 데이터베이스와 테이블이 생성됩니다.

기본 설정:
- 데이터베이스명: `material_db`
- 호스트: `localhost:3306`
- 사용자: `root`
- 비밀번호: `root`

설정을 변경하려면 `src/main/java/com/materials/config/DatabaseConfig.java` 파일을 수정하세요.

## 빌드 및 실행

### 1. 프로젝트 빌드
```bash
mvn clean package
```

### 2. 애플리케이션 실행
```bash
java -jar target/material-management-swing-1.0.0.jar
```

또는 Maven으로 직접 실행:
```bash
mvn exec:java -Dexec.mainClass="com.materials.Main"
```

## 테스트 계정

### 관리자 계정
- 사용자명: `admin`
- 비밀번호: `admin1234`

### 일반 사용자 계정
- 사용자명: `user1`
- 비밀번호: `user1234`

## 프로젝트 구조
```
src/main/java/com/materials/
├── Main.java                          # 애플리케이션 진입점
├── config/
│   └── DatabaseConfig.java            # 데이터베이스 설정
├── dao/
│   ├── MaterialDAO.java               # 자재 데이터 액세스
│   ├── StockHistoryDAO.java           # 입출고 이력 데이터 액세스
│   └── UserDAO.java                   # 사용자 데이터 액세스
├── model/
│   ├── Material.java                  # 자재 모델
│   ├── StockHistory.java              # 입출고 이력 모델
│   └── User.java                      # 사용자 모델
├── ui/
│   ├── LoginFrame.java                # 로그인 화면
│   ├── MainFrame.java                 # 메인 화면
│   └── panel/
│       ├── DashboardPanel.java        # 대시보드 패널
│       ├── MaterialPanel.java         # 자재 관리 패널
│       ├── StockHistoryPanel.java     # 입출고 이력 패널
│       ├── UserPanel.java             # 사용자 관리 패널
│       ├── MaterialDialog.java        # 자재 추가/수정 다이얼로그
│       ├── UserDialog.java            # 사용자 추가/수정 다이얼로그
│       └── StockTransactionDialog.java # 입출고 처리 다이얼로그
└── util/
    └── SessionManager.java            # 세션 관리
```

## 화면 설명

### 1. 로그인 화면
- 사용자 인증
- 모던한 디자인
- 테스트 계정 정보 표시

### 2. 대시보드
- 총 자재 수, 재고 부족 자재, 최근 거래 건수 통계
- 재고 부족 자재 목록
- 최근 입출고 이력

### 3. 자재 관리
- 자재 목록 테이블
- 검색 기능
- 자재 추가/수정/삭제
- 입고/출고 처리

### 4. 입출고 이력
- 전체 입출고 이력 조회
- 구분별 필터링 (전체/입고/출고/조정)

### 5. 사용자 관리 (관리자만)
- 사용자 목록 조회
- 사용자 추가/수정
- 비밀번호 변경
- 계정 활성화/비활성화
- 권한 설정 (관리자/사용자)

### 5. 사용자 관리 (관리자 전용)
- 사용자 목록 조회
- 사용자 추가 (사용자명, 비밀번호, 이름, 이메일, 전화번호, 권한)
- 사용자 정보 수정
- 비밀번호 변경
- 사용자 삭제 (비활성화)

## 특징

### 모던한 UI/UX
- FlatLaf 테마 적용
- 직관적인 인터페이스
- 반응형 레이아웃
- 색상 구분 (입고: 녹색, 출고: 주황색)

### 데이터 무결성
- 트랜잭션 처리
- 재고 부족 검증
- 필수 입력 검증

### 보안
- BCrypt 비밀번호 암호화
- 세션 기반 인증
- 권한별 접근 제어

## 문제 해결

### MySQL 연결 오류
```
Connection refused: connect
```
해결 방법:
1. MySQL 서버가 실행 중인지 확인
2. 포트 번호 확인 (기본: 3306)
3. 사용자명/비밀번호 확인

### 한글 깨짐 현상
데이터베이스와 테이블이 UTF-8로 설정되었는지 확인하세요.

## 라이선스
이 프로젝트는 MIT 라이선스를 따릅니다.

## 개발자
Material Management System v1.0

## 업데이트 내역
- v1.0.0 (2024-12-11): 초기 버전 릴리스
