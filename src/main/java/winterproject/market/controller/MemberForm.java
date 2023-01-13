package winterproject.market.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {
    @NotEmpty
    private String id;
    private String password;
    private String email;
    private String nickname;
}
