package winterproject.market.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import winterproject.market.controller.MemberForm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Slf4j
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

    public Member(MemberForm memberForm, BindingResult bindingResult) throws IllegalStateException{
        checkPassword(memberForm, bindingResult);
        this.id = memberForm.getId();
        this.password = memberForm.getPassword();
        this.email = memberForm.getEmail();
        this.nickname = memberForm.getNickname();
    }

    private void checkPassword(MemberForm memberForm, BindingResult bindingResult) {
        if (!memberForm.getPassword().equals(memberForm.getPasswordRepeat()))
        {
            bindingResult.addError(new FieldError("password", "password", "비밀번호가 일치하지 않습니다."));
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
    }


    public Member() {

    }

    public void update(MemberForm memberForm, BindingResult bindingResult) {
        checkPassword(memberForm, bindingResult);
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }
}
