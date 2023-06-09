# 2022_Project_PM_Plus
본 프로젝트는 한이음측에서 주최한 스마트 해상 물류 공모전에 참여했던 프로젝트입니다.   
대학교 2학년 때 처음으로 만들어 본 애플리케이션으로 완성도가 낮습니다.   
코드가 엉망인 부분도 많고, 효율적이지 못한 부분들도 많아 미숙하지만,   
이 경험은 앱 개발자로 진로를 결정하게 된 계기가 되었습니다.   

커밋은 깃랩에서 진행을 해, 깃허브에는 올라가지 않았습니다. (방법이 분명 있을텐데, 못한 것 같네요 😅)   
그래서 종료 된 후, 코드를 한꺼번에 올립니다.     

>진행 : 2022/04/15 ~ 2022/11/30   
>애플리케이션 명 : PM+(PortMis Plus)      
>개발 인원 : 4명   
>멘토 : 1명     
>시뮬레이션 영상 링크 : [2022 스마트 해상물류 경진대회](https://www.youtube.com/watch?v=_mnuw_442y8)

## 프로젝트 소개
"PORTMIS PLUS+"는 모바일 항만 종합 정보 제공 애플리케이션으로 해운 항만 물류정보시스템과 해양 안전 종합정보시스템의 주요 기능 및 부가적인 서비스를 제공하는 서비스입니다.   편의 개선 및 접근성을 향상시키는 것을 목적으로 개발되었습니다.    


## 역할
"통합민원"팀에서 리드 개발자로 활동하며 팀원을 이끌었습니다.   
프로젝트를 진행하며 경험해 본 기술 스택은 다음과 같습니다.
1. 기획   
2. 화면설계 (figma, Abode potoshop)   
3. 안드로이드 스튜디오 작업 (Java)
4. 데이터 베이스 작업 (firebase)   
5. AWS EC2 관리   

## 화면설계서 (아래 이미지 참고)
<img src= "https://user-images.githubusercontent.com/83949732/234164081-8a24c9fc-58f5-4537-8b05-5af6d8049c22.jpg">


## 서비스 목록 
1. 로그인&회원가입   
   >구현 : firebase, android studio   
   >요구 된 정보 : 이름, 아이디, 비밀번호, 이메일   
2. 선박 입출항 조회   
   >구현 : android studio - xmlParser   
   >데이터 출처 : 공공데이터 포털   
3. 선박 관제 현황 조회   
   >구현 : android studio - xmlParser   
   >데이터 출처 : 공공데이터 포털   
4. 날씨 조회   
   >구현 : android studio - xmlParser   
   >데이터 출처 : 공공데이터 포털 
5. 게시판 기능
   >구현 : firebase, android studio     
6. 해양안전 공지사항
   >구현 : android studio -    
   >데이터 출처 : 해양안전 종합정보 사이트 공지사항 부분 크롤링   
7. 챗봇 기능 (담당 부분 x)
   >구현 : dialogflow     
   >데이터 출처 : 공공데이터 포털 
  


