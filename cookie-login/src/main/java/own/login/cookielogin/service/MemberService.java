package own.login.cookielogin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import own.login.cookielogin.domain.LoginForm;
import own.login.cookielogin.domain.Member;
import own.login.cookielogin.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member join(LoginForm loginForm) {
        return memberRepository.join(loginForm);
    }

    public Member login(LoginForm loginForm) {
        Member findMember = findByLoginId(loginForm.getLoginId());
        System.out.println(findMember);
        System.out.println(findMember.getPassword());
        System.out.println(loginForm.getPassword());
        if (findMember.getPassword().equals(loginForm.getPassword())) {
            return findMember;
        }
        return null;
    }

    public Member findByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }
}
