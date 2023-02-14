package winterproject.market.Repository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import winterproject.market.domain.Item;

import javax.persistence.EntityManager;
import winterproject.market.domain.Member;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ItemRepository {
    private final EntityManager em;

    public Long save(Item item) {
        item.getMember().getItems().add(item);
        em.persist(item);
        return item.getId();
    }

    public Long delete(Long id){
        em.remove(id);
        return id;
    }

    public Item findById(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAllByMember(Member member) {
        return member.getItems();
    }
}
