package own.login.cookielogin;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import own.login.cookielogin.domain.Member;
import own.login.cookielogin.service.MemberService;

@SpringBootApplication
public class CookieLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(CookieLoginApplication.class, args);
    }

}
