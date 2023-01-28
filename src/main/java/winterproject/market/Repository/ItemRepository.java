package winterproject.market.Repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import winterproject.market.domain.Item;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
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
}
