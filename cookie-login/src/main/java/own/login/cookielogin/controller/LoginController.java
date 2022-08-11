package own.login.cookielogin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import own.login.cookielogin.domain.LoginForm;
import own.login.cookielogin.domain.Member;
import own.login.cookielogin.service.MemberService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/")
    public String homeLogin(@CookieValue(name = "memberId", required = false) String memberId, Model model) {
        if (memberId == null) {
            return "home";
        }

        Member member = memberService.findByLoginId(memberId);
        if (member == null) {
            return "home";
        }

        model.addAttribute("member", member);
        return "loginHome";
    }

    @GetMapping("join")
    public String joinForm() {
        return "login/joinForm";
    }

    @PostMapping("join")
    public String joinReq(@ModelAttribute LoginForm form) {
        memberService.join(form);
        return "redirect:/login";
    }

    @GetMapping("login")
    public String loginForm() {
        return "login/loginForm";
    }


    @PostMapping("login")
    public String login(@Valid @ModelAttribute LoginForm form,
                        BindingResult bindingResult,
                        HttpServletResponse response) {

        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        System.out.println(form);

        Member member = memberService.login(form);

        if (member == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        Cookie idCookie = new Cookie("memberId", member.getLoginId());
        response.addCookie(idCookie);

        return "redirect:/";

    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        expiredCookie(response, "memberId");
        return "redirect:/";
    }

    private void expiredCookie(HttpServletResponse resp, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
    }
}
