package winterproject.market.controller;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {
    @NotEmpty(message = "아이디가 입력되지 않았습니다.")
    private String id;

    @NotEmpty(message = "비밀번호가 입력되지 않았습니다.")
    private String password;

    @NotEmpty(message = "이메일이 입력되지 않았습니다.")
    private String email;

    @NotEmpty(message = "닉네임이 입력되지 않았습니다.")
    private String nickname;
}
