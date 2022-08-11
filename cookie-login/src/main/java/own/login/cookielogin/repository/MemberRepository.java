package own.login.cookielogin.repository;

import own.login.cookielogin.domain.LoginForm;
import own.login.cookielogin.domain.Member;

public interface MemberRepository {

    public Member join(LoginForm loginForm);

    public Member findByLoginId(String loginId);


}
