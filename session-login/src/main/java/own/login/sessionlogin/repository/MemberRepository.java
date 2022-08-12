package own.login.sessionlogin.repository;


import own.login.sessionlogin.domain.LoginForm;
import own.login.sessionlogin.domain.Member;

public interface MemberRepository {

    public Member join(LoginForm loginForm);

    public Member findByLoginId(String loginId);


}
