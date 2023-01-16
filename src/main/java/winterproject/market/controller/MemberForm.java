package winterproject.market.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {
    @NotEmpty(message = "id는 필수입니다.")
    private String id;
    private String password;
    private String email;
    private String nickname;
}
