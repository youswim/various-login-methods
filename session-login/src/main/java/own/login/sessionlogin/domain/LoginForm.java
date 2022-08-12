package own.login.sessionlogin.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {

    private String loginId;
    private String password;

    @Override
    public String toString() {
        return "LoginForm{" +
                "loginId='" + loginId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
