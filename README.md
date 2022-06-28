# myshop
지금까지 학습한 내용을 정리한 백엔드 토이 프로젝트

주요 내용들은 추후 업데이트 예정


## 💻 Use Stack 
사용언어 : <img alt="Java" src ="https://img.shields.io/badge/Java-007396.svg?&style=for-the-badge&logo=Java&logoColor=white"/> 

사용기술 : <img alt="Spring" src ="https://img.shields.io/badge/Spring-6DB33F.svg?&style=for-the-badge&logo=Spring&logoColor=white"/> <img alt="Spring Boot" src ="https://img.shields.io/badge/Spring Boot-6DB33F.svg?&style=for-the-badge&logo=Spring Boot&logoColor=white"/> <img alt="Spring Data Jpa" src ="https://img.shields.io/badge/Spring Data Jpa-6DB33F.svg?&style=for-the-badge&logo=Spring&logoColor=white"/> <img alt="Spring 20Security" src ="https://img.shields.io/badge/Spring%20Security-6DB33F.svg?&style=for-the-badge&logo=Spring&logoColor=white"/>


데이터베이스 : <img alt="h2database" src ="https://img.shields.io/badge/h2database-6DB33F.svg?&style=for-the-badge&logo=&logoColor=white"/>

뷰 템플릿 : <img alt="Thymeleaf" src ="https://img.shields.io/badge/Thymeleaf-005F0F.svg?&style=for-the-badge&logo=Thymeleaf&logoColor=white"/>

IDE : <img alt="IntelliJ IDEA" src ="https://img.shields.io/badge/IntelliJ IDEA-000000.svg?&style=for-the-badge&logo=IntelliJ IDEA&logoColor=white"/>
 
<br/>

## 📖 상세 화면

<div align="center">
  
</div>


<br/>
<br/>

> 🍽️ 해당 프로젝트의 스프링 시큐리티는 패스트캠퍼스의 java/spring 강의를 보고 참고하였습니다.<br/>
참고한 깃허브주소  [sp-fastcampus-spring-sec](https://github.com/LEEWONHOI/sp-fastcampus-spring-sec)<br>

<br/>
<br/>



## 📱 구현한 기능

- 일반회원 / 매니저회원 가입 기능
- 매니저 회원으로 가입 시 DB 에 영화 정보를 추가할 수 있는 기능
- 일반 회원이 영화정보 추가 페이지에 접근 시 Authority 를 체크하여 reject 하는 기능
- 클라이언트에서 영화 검색 요청이 오는 경우 조건에 맞춰서 영화 목록을 Json 데이터로 보내주는 기능
- 클라이언트에서 영화 디테일 정보 요청이 오는 경우 영화 디테일 정보를 Json 데이터로 보내주는 기능


<br/>
<br/>

## 💡 인상깊었던 부분
- SSR 방식의 controller 사용은 경험해보았지만 CSR 방식의 RestController 사용은 처음이였기에 Json 객체 전달방법, Cors 문제 등 여러가지 새로운 문제들을 접해볼 수 있었고, 새로운 기술을 사용해볼 수 있어서 흥미로웠습니다.
- Entity 설계하며 단방향 방식과 양방향 방식에 대해 고민과 더 나은 설계에 대해서 많은 고민이 있었습니다. 더 많은 프로젝트 경험을 쌓으며 경험적인 추론이 가능한 실력의 필요성을 느꼈습니다.
- 비교적 쉽다는 vuejs를 학습하며 간단한 프론트엔드를 구현해보았습니다. 인터넷 강의에서 학습한 템플릿을 기반으로 새로운 부분을 추가하면서 프론트 영역에 대한 지식을 학습했습니다.
프론트엔드에서 api 로 전달된json을 어떤식으로 다루는지, 서버에서 전달되는 에러는 어떻게 처리하는지 등 백엔드와 프론트엔드의 관계를 학습할 수 있었습니다. 

<br/>
<br/>

작업중인 프론트엔드 깃허브 주소[movie-review-website-frontend-vue3](https://github.com/LEEWONHOI/movie-review-website-frontend-vue3)<br>

