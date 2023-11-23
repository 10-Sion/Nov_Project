# 부산 지역 커뮤니티 (부산 진구 치과 리뷰 커뮤니티)

|<img src = "https://github.com/10-Sion/September_Project/assets/140682709/ff3216fc-1224-47a5-890a-0440a39f22d8" width="1000">|
|:---:|
|메인 페이지|


## 🖥️ 프로젝트 소개
부산 진구에 위치한 치과 전체 목록을 공공 API로 받아와 리뷰를 작성하고 소통하는 커뮤니티 페이지입니다.

## 목차
  - [프로젝트 개요](#프로젝트-개요) 
  - [프로젝트 ERD 구성](#프로젝트-ERD-구성)
  - [프로젝트 구현]
     - [전영혜](#전영혜)
     - [박준현](#박준현)
     - [김소이](#김소이)
     - [정현도](#정현도)
   
## 프로젝트 개요
- 프로젝트 이름: Nov_Project
- 프로젝트 지속기간: 2023.10.24 ~ 2023.11.17
- 개발 엔진 및 언어: HTML, CSS, JSP, JAVA, JavaScript, Jquery, Ajax / Eclipse, tomcat, MySQL, Git 
- 멤버: 김민성, 김소이, 김재우, 박준현

## 프로젝트 ERD 구성

|<img width="500" alt="그림1" src="https://github.com/10-Sion/September_Project/assets/140682709/c0d16f35-3f27-4129-ad24-15a517ad3f2e">|<img width="500" alt="그림2" src="https://github.com/10-Sion/September_Project/assets/140682709/101a3d9d-1b0f-4b7e-a337-eedebf6e5f79">|
|:---:|:---:|


## 📌 주요 기능
#### 로그인 및 회원가입
1. 세션 처리
2. 아이디 저장 (쿠키활용)
3. 회원가입 시 유효성 검사

#### 메인 페이지
1. 현재 위치 기반 근처 1KM 약국 리스트 구현
2. 페이지 상단 검색창 기능 구현
3. 알쏭달쏭 건강퀴즈 10문제 구현

#### 병원 메인 페이지
1. 부산 진구 전체 치과 리스트 출력 페이지 (공공 API적용)
2. 리뷰 작성 페이지 - "동" 선택 시, 해당 동에 위치한 치과 목록 선택 후 리뷰 작성
3. 리뷰 작성 시 영수증 파일 업로드 시 관리자에게 전송
4. 인증완료 상태 모든 리뷰 리스트 출력 페이지

#### 커뮤니티 (각 게시판 수정,조회,삭제 가능)
1. 자유게시판 기능 (페이징) - 댓글 작성 및 조회, 수정
2. 공지사항 페이지 (관리자만 글 작성 가능)
3. 건의사항 페이지 (일반 사용자 문의 글 작성 / 관리자 확인)
   

## 📖 기능 구현

## 전영혜 
- 정적 레이아웃, 회원 타입 별 처리와 
마이페이지 전반, PPT 제작

1. 로그인 및 회원가입 기능, 개인 정보 수정, 
회원 타입 별로 특정 메뉴 숨김, 보임 처리(권한 구분)

2. 커뮤니티 내 글 작성부 스마트에디터 적용 
    푸터 레이아웃과 공용 아이콘 디자인, PPT 제작


|<img src = "https://github.com/10-Sion/September_Project/assets/140682709/8846495b-0eef-47f5-8e29-a9b0140790ae" width="500">|<img src = "https://github.com/10-Sion/September_Project/assets/140682709/48b3f3aa-bf79-4d7c-9e88-473313be984f" width="500">
|:---:|:---:|

|<img src = "https://github.com/10-Sion/September_Project/assets/140682709/9d04b2f7-80ca-4f89-a54c-7c741a89975a" width="500">|<img src = "https://github.com/10-Sion/September_Project/assets/140682709/ed817c4c-d418-48bd-bf3d-5bfddd79dcc0" width="500">
|:---:|:---:|


## 박준현
- 페이지 레이아웃 및 동적처리 전반, 메인과 이벤트

1. 페이지 시작-메인페이지, 이후 상단/측면 공통 영역
   메인페이지 내 서블릿 및 API 다중요청 상태관리
   Google Custom Search API, 공공데이터포털

2. 이벤트 퀴즈 페이지 – JSON 통해 랜덤 배열 형태 가져와 출제


|<img src = "https://github.com/10-Sion/September_Project/assets/140682709/27f271bf-039a-4a20-8e09-480f339166c8" width="500">|<img src = "https://github.com/10-Sion/September_Project/assets/140682709/f5020c80-3dfd-4543-8db2-ebda1e21995e" width="500">
|:---:|:---:|

|<img src = "https://github.com/10-Sion/September_Project/assets/140682709/9a6ba353-e498-4d41-9933-c8b3346bd41a" width="500">|<img src = "https://github.com/10-Sion/September_Project/assets/140682709/94f70070-5384-4f3b-8024-fa4810892d09" width="500">
|:---:|:---:|


## 김소이
- 병원 리뷰 및 병원 지도, 길찾기, 데이터베이스 전반

1. 데이터베이스 구축 및 공공데이터

2. 병원 메인 페이지 전체 구축 및 레이아웃, CSS
병원 리뷰 작성 페이지 구축 (파일 업로드)
병원 전체 리스트 구현 및 관련 리뷰 리스트 페이지 이동

3. API 지도 및 길찾기


|<img src = "https://github.com/10-Sion/September_Project/assets/140682709/0412a0ed-446d-46f7-b211-50b5a8deb0e6" width="1000">|
|:---:|
|병원 메인페이지|


|<img src = "https://github.com/10-Sion/September_Project/assets/140682709/99a3e6ab-efa0-47b0-a5f2-08c965086c21" width="1000">|
|:---:|
|병원 지도 및 길찾기 API|


|<img src = "https://github.com/10-Sion/September_Project/assets/140682709/752c0b14-a0eb-4747-89e9-e573a949540d" width="500">|<img src = "https://github.com/10-Sion/September_Project/assets/140682709/93edbc34-8b68-4b79-8390-cd5f4b530705" width="500">
|:---:|:---:|

|<img src = "https://github.com/10-Sion/September_Project/assets/140682709/4e720c9a-69b8-44da-940b-b0daa1cb3489" width="500">|<img src = "https://github.com/10-Sion/September_Project/assets/140682709/ba842881-c45a-4977-892a-c57e1e5a1717" width="500">
|:---:|:---:|

|<img src = "https://github.com/10-Sion/September_Project/assets/140682709/d1cac202-0458-4d39-b510-85db905a73ad" width="500">|<img src = "https://github.com/10-Sion/September_Project/assets/140682709/3ef954d5-80aa-40b6-a68a-fff482d743a4" width="500">
|:---:|:---:|


## 정현도
- 커뮤니티 (공지, 건의, 자유) 와 관리 페이지 전반
- 
1. 커뮤니티 게시판 조회, 수정, 삭제 기능 구현
   자유게시판 내 대댓글, 추천 / 비추천 구현

2. 리뷰절차 및 가입자 관리 페이지 구현
    승인 대기 리뷰의 승인 / 거절 기능 구현

|<img src = "https://github.com/10-Sion/September_Project/assets/140682709/6d412a2c-35b5-4eec-a739-cb9941c75018" width="500">|<img src = "https://github.com/10-Sion/September_Project/assets/140682709/9443055d-c3dd-4b10-b427-b8ad15e7583b" width="500">
|:---:|:---:|

|<img src = "https://github.com/10-Sion/September_Project/assets/140682709/07182725-58b9-49a6-99c4-25076b118ee2" width="500">|<img src = "https://github.com/10-Sion/September_Project/assets/140682709/495d2c20-e5df-428d-ada0-eda764926802" width="500">
|:---:|:---:|






