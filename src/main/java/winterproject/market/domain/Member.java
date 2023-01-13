package winterproject.market.domain;

import lombok.Getter;
import lombok.Setter;
import winterproject.market.controller.MemberForm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id
    @Column(name = "member_id")
    private String id;
    private String password;
    private String email;
    private String nickname;

    @OneToMany(mappedBy = "member")
    private List<Item> items = new ArrayList<>();

    public Member(MemberForm memberForm) {
        this.id = memberForm.getId();
        this.password = memberForm.getPassword();
        this.email = memberForm.getEmail();
        this.nickname = memberForm.getNickname();
    }

    public Member() {

    }
}
