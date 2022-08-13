https://webfirewood.tistory.com/115<br>
위 블로그를 참고해서 만든 JWT 로그인

h2 db에는 user가 예약어로 잡혀있어서, user테이블을 만들 때 오류가 발생했었음.
@Entity(name="USERS")을 지정해서 해결.