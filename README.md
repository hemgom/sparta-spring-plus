# SPRING PLUS
`JPA 성능 최적화`, `QueryDSL 을 활용한 동적쿼리 작성`, `Spring Security` 등 실무에서 자주 사용되는 `패턴, 기술` 과 `AWS` 의 다양한 서비스를 학습하기 위한 프로젝트
<br/><br/><br/>

# 요구사항
## 필수 기능
### Level.01
- [x] : 코드 개선 퀴즈 - `@Transactional` 의 이해
  - `TodoController` 의 `saveTodo()` 가 정상작동 할 수 있도록 코드를 수정
- [x] : 코드 추가 퀴즈 - `JWT` 의 이해
  - `User` 에 `nickname` 필드 추가
  - `JWT` 를 통해 `User.nickname` 정보를 알 수 있도록 `JWTUtil` 수정
- [x] : 코드 개선 퀴즈 - `AOP` 의 이해
  - `AdminAccessLoggingAspect` 클래스의 `logAfterChangeUserRole()` 을 수정
    - `UserAdminController` 클래스의 `changeUserRole() 실행 전` 에 `logAfterChangeUserRole()` 가 수행되어야 함
- [x] : 테스트 코드 퀴즈 - 컨트롤러 테스트의 이해
  - 테스트 패키지 `org.example.expert.domain.todo.controller` 의 `todo_단건_조회_시_todo가_존재하지_않아_예외가_발생한다()` 테스트를 수정
    - 해당 테스트가 정상적으로 통과할 수 있어야 함
- [x] : 코드 개선 퀴즈 - JPA 의 이해
  - `API(GET /todos)` 요청시 아래의 내용을 만족할 수 있도록 코드를 수정
    - `weather` 를 검색 조건을 사용할 수 있어야 한다.
      - 단, 검색시 `weather` 조건이 있던 없던 검색이 가능해야 한다.
    - `modifiedAt(수정일)` 기준으로 `기간 검색` 이 가능해야 한다.
      - 검색할 `기간` 에 대한 조건이 있던 없던 검색이 가능해야 한다.
    - `JPQL` 을 사용하고, `쿼리 메서드명` 은 자유롭게 지정가능하나 너무 길지 않도록 한다.
<br/>

### Level.02
- [x] : JPA Cascade
  - 실수로 작제된 코드를 다시 작성한다. 작성할 코드는 아래의 조건을 만족해야 한다.
    - `JPA` 의 `cascade` 기능을 활용해 일정을 생성한 유저가 `자동으로 담당자로 등록` 될 수 있도록 해야 한다.
- [x] : N+1
  - `API(GET /todos/{todoId}/comments)` 요청시 발생하고 있는 `N+1` 문제를 해결할 수 있도록 코드를 수정
<br/><br/><br/>

# 프로젝트 관련 포스팅
- [5분 기록 테이블](https://development-diary-for-me.tistory.com/192)
- [Level 1-1 : 코드 개선 퀴즈 - @Transactional 의 이해](https://development-diary-for-me.tistory.com/193)
- [Level 1-2 : 코드 추가 퀴즈 - JWT 의 이해](https://development-diary-for-me.tistory.com/195)
- [Level 1-3 : 코드 개선 퀴즈 - AOP 의 이해](https://development-diary-for-me.tistory.com/196)
- [Level 1-4 : 테스트 코드 퀴즈 - 컨트롤러 테스트의 이해](https://development-diary-for-me.tistory.com/197)
- [Level 1-5 : 코드 개선 퀴즈 - JPA 의 이해](https://development-diary-for-me.tistory.com/198)
- [Level 2-1 : JPA Cascade](https://development-diary-for-me.tistory.com/199)
- [Level 2-2 : N+1](https://development-diary-for-me.tistory.com/200)