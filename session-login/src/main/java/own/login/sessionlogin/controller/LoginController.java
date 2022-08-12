package own.login.sessionlogin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import own.login.sessionlogin.domain.LoginForm;
import own.login.sessionlogin.domain.Member;
import own.login.sessionlogin.service.MemberService;
import own.login.sessionlogin.session.SessionConst;
import own.login.sessionlogin.session.SessionManager;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;
    private final SessionManager sessionManager = new SessionManager();

    @GetMapping("/")
    public String homeLogin(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)Member member,
            HttpServletRequest req, Model model) {

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


//    @PostMapping("login")
//    public String login(@Valid @ModelAttribute LoginForm form,
//                        BindingResult bindingResult,
//                        HttpServletResponse response) {
//
//        if (bindingResult.hasErrors()) {
//            return "login/loginForm";
//        }
//
//        Member member = memberService.login(form);
//
//        if (member == null) {
//            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
//            return "login/loginForm";
//        }
//
//        sessionManager.createSession(member, response);
//        return "redirect:/";
//
//    }

    @PostMapping("login")
    public String loginV2(@Valid @ModelAttribute LoginForm form,
                        BindingResult bindingResult,
                        HttpServletRequest req,
                        HttpServletResponse response) {

        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Member member = memberService.login(form);
        System.out.println(member);

        if (member == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        System.out.println("세션 반환!");
        HttpSession session = req.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, member);
        return "redirect:/";

    }

//    @PostMapping("/logout")
//    public String logout(HttpServletRequest req) {
//        sessionManager.expire(req);
//        return "redirect:/";
//    }

    @PostMapping("/logout")
    public String logoutV2(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }
}
