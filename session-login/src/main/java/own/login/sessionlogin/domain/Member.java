package own.login.sessionlogin.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {

    private Long id;

    private String loginId;

    private String password;

}
