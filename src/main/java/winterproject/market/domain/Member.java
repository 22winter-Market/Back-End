package winterproject.market.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id
    @Column(name = "member_id")
    private long id;
    private String password;
    private String email;
    private String nickname;

    @OneToMany(mappedBy = "member")
    private List<Item> items = new ArrayList<>();
}
