package winterproject.market.Service;

import lombok.Getter;
import lombok.Setter;
import winterproject.market.domain.Member;

@Getter
@Setter
public class AuthInfo {
    private final String id;
    private final String email;
    private final String nickname;

    public AuthInfo(Member member) {
        id = member.getId();
        email = member.getEmail();
        nickname = member.getNickname();
    }
}
