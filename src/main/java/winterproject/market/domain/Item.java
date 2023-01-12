package winterproject.market.domain;

import lombok.Getter;
import winterproject.market.domain.enumeration.TradeMethod;

import javax.persistence.*;

@Entity
@Getter
public class Item {

    @Id
    @Column(name = "item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    private String title;
//    private String imageUrl;

    private String category;
    private int price;

    @Enumerated(EnumType.STRING)
    private TradeMethod tradeMethod;

    private String tradePlace;
}
