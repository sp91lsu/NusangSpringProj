### Commit Author : SunBi91 <sp91lsu@gmail.com> or 이성웅 <dev.lsw91@gmail.com>
# 프로젝트 개요
* 1km어플을 벤치마킹한 이성만남 플랫폼 사이트

# 프로젝트 기간
* 2020-11-21 ~ 2020-12-08 (약3주)
  
# 역할분담
* 이승환 - 소셜로그인, 상점, 채팅, 친구<br>
* 윤종운 - 글, 댓글<br>
* 박성훈 - 프로필<br>
* **이성웅 - 관리자(회원검색, 매출통계)**<br>
* 김천의 - 관리자(공지사항, FAQ, QNA)
 
# 개발 환경
* Spring Boot (정규 교육을 받진 않았고 팀장에게 배워서 썼습니다.)
* JPA (정규 교육을 받진 않았고 팀장에게 배워서 썼습니다.)
* JSP
* Java
* Oracle, SQL Developer
* Sourcetree (정규 교육을 받진 않았고 팀장에게 배워서 썼습니다.)
* AJAX
* JACKSON
* Eclipse
* Apache Tomcat
* HTML, CSS, JS, JQuery, Bootstrap

# 이성웅 - 중점개발내용
   테스트 데이터 생성 (프로젝트 기간의 약70% 할애)
   * 목적: DB의 USER Table과 PAYMENT Table에 각각 10000개 ROW 이상의 다양한 테스트 데이터를 삽입해서 회원검색과 매출통계를 수행할 때 실제처럼 대량의 데이터가 들어갔을 때에도 잘 작동하는지 확인하기 위함.
   * 사용예시: 
     * 아래와 같이 Parameter를 Customizing한 URL로 접속하면 입력조건에 맞게 DB와 Transaction하여 데이터를 생성함. <br> localhost:8000/testdata/insert?uRowCnt=13000&pRowCnt=18000&uDateRange=1100&pDateRange=1100 
     * Parameter 설명: 
       * uRowCnt: 생성할 USER 테이블의 rowdata 개수
       * pRowCnt: 생성할 PAYMENT 테이블의 rowdata 개수
       * uDateRange: USER 테이블의 regdate를 랜덤으로 생성할 때 필요한 범위값. 시스템 date 부터 몇일전까지 regdate를 랜덤으로 생성할지에 대한 날짜기간 수
       * pDateRange: PAYMENT 테이블의 regdate를 랜덤으로 생성할 때 필요한 범위값. 시스템 date 부터 몇일전까지 regdate를 랜덤으로 생성할지에 대한 날짜기간 수     
   * 해당 코드
     * [컨트롤러](https://github.com/sp91lsu/NusangSpringProj/blob/master/blog/src/main/java/com/mycom/blog/controller/manager/TestDataInsert.java)
     * [TestDataService.java](https://github.com/sp91lsu/NusangSpringProj/blob/master/blog/src/main/java/com/mycom/blog/service/testData/TestDataService.java)
     * [DataList.java](https://github.com/sp91lsu/NusangSpringProj/blob/master/blog/src/main/java/com/mycom/blog/service/testData/DataList.java)

   

# 이성웅 - 발표 영상
[![매출통계2](https://user-images.githubusercontent.com/66866793/109481354-fa343600-7abf-11eb-9e11-a8212ed8627e.png)](https://www.youtube.com/watch?list=PLedGoSru794-VZTZQNBvqYCNlfP9w48Ly&t=1236&v=RfYbYb1eyyw&feature=youtu.be)
