package winterproject.market.controller;

import lombok.Getter;
import lombok.Setter;
import winterproject.market.domain.Member;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {

    @NotEmpty(message = "아이디가 입력되지 않았습니다.")
    private String id;

    @NotEmpty(message = "비밀번호가 입력되지 않았습니다.")
    private String password;

    @NotEmpty(message = "비밀번호가 입력되지 않았습니다.")
    private String passwordRepeat;

    @NotEmpty(message = "이메일이 입력되지 않았습니다.")
    private String email;

    @NotEmpty(message = "닉네임이 입력되지 않았습니다.")
    private String nickname;

    public MemberForm(Member member) {
        id = member.getId();
        email = member.getEmail();
        nickname = member.getNickname();
    }

    public MemberForm() {

    }
}
