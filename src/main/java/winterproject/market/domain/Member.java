package winterproject.market.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id
    @Column(name = "member_id")
    private long id;
    private String password;
    private String email;
    private String nickname;

    private List<Item> items = new ArrayList<>();
}
