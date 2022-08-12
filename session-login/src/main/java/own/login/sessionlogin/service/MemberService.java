package own.login.sessionlogin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import own.login.sessionlogin.domain.LoginForm;
import own.login.sessionlogin.domain.Member;
import own.login.sessionlogin.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member join(LoginForm loginForm) {
        return memberRepository.join(loginForm);
    }

    public Member login(LoginForm loginForm) {
        Member findMember = findByLoginId(loginForm.getLoginId());
        if (findMember.getPassword().equals(loginForm.getPassword())) {
            return findMember;
        }
        return null;
    }

    public Member findByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }
}
