package own.login.sessionlogin.repository;

import org.springframework.stereotype.Repository;
import own.login.sessionlogin.domain.LoginForm;
import own.login.sessionlogin.domain.Member;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static final Map<String, Member> memberMap = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Member join(LoginForm loginForm) {
        Member member = new Member();
        member.setId(++sequence);
        member.setLoginId(loginForm.getLoginId());
        member.setPassword(loginForm.getPassword());
        memberMap.put(member.getLoginId(), member);
        System.out.println("memberMap = " + memberMap);
        return member;
        // 중복검사는 생략
    }

    @Override
    public Member findByLoginId(String loginId) {
        for (String key : memberMap.keySet()) {
            if (loginId.equals(key)) {
                return memberMap.get(loginId);
            }
        }
        return null;
    }
}
