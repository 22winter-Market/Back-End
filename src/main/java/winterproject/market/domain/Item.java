package winterproject.market.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import winterproject.market.controller.ItemForm;
import winterproject.market.domain.enumeration.ItemStatus;
import winterproject.market.domain.enumeration.TradeMethod;

import javax.persistence.*;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    private String title;
//    private String imageUrl;

    private String contents;
    private int price;

    @Enumerated(EnumType.STRING)
    private TradeMethod tradeMethod;

    private String tradePlace;

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus = ItemStatus.SALE;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime lastUpdatedDate;

    @OneToMany
    private List<UploadFile> uploadFiles = new ArrayList<>();

    public void soldOut() {
        this.itemStatus = ItemStatus.SOLD_OUT;
    }

    public Item(Member member, ItemForm itemForm) {
        this.member = member;
        title = itemForm.getTitle();
        contents = itemForm.getContents();
        price = itemForm.getPrice();
        tradeMethod = TradeMethod.of(itemForm.getTradeMethod());
        tradePlace = itemForm.getTradePlace();
    }

    public Item() {

    }
}
