package winterproject.market.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LoginForm {

    @NotEmpty(message = "id가 입력되지 않았습니다.")
    private String id;

    @NotEmpty(message = "비밀번호가 입력되지 않았습니다.")
    private String password;

    private String error;
}
