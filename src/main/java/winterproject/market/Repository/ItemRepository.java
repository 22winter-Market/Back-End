package winterproject.market.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import winterproject.market.domain.Item;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ItemRepository {
    private final EntityManager em;

    public Long save(Item item) {
        log.info("item : " + item.toString());
        log.info("member : " + item.getMember());
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
}
