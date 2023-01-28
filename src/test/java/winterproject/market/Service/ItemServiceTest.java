package winterproject.market.Service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import winterproject.market.Repository.MemberRepository;
import winterproject.market.controller.ItemForm;
import winterproject.market.domain.Item;
import winterproject.market.domain.Member;

import java.util.List;

@SpringBootTest
@Transactional
//@Rollback(value = false)
class ItemServiceTest {

    @Autowired private MemberRepository memberRepository;
    @Autowired private ItemService itemService;


    @BeforeEach
    void joinMember() {
        Member member = new Member();
        member.setId("yg0585");
        memberRepository.save(member);
    }

    @Test
    void 아이템_등록() {
        ItemForm itemForm = new ItemForm();
        itemForm.setContents("contents");
        itemForm.setPrice(10000);
        itemForm.setTitle("title");
        itemForm.setMemberId("yg0585");
        itemForm.setTradeMethod("ONLINE");

        Long id = itemService.itemUpload(itemForm);
        Item findItem = itemService.findItemById(id);

        Assertions.assertThat(findItem.getId()).isEqualTo(id);

        Member yg0585 = memberRepository.findOneById("yg0585");
        List<Item> items = yg0585.getItems();

        Assertions.assertThat(items.size()).isEqualTo(1);
    }
}