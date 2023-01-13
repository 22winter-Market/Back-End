package winterproject.market.Repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import winterproject.market.domain.Member;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findById(String id) {
        return em.find(Member.class, id);
    }
}
