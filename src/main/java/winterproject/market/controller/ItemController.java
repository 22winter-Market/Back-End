package winterproject.market.controller;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import winterproject.market.Service.ItemService;
import winterproject.market.Service.MemberService;
import winterproject.market.domain.Item;
import winterproject.market.domain.Member;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;
    private final MemberService memberService;

    @GetMapping("items/additem") // url에 matching
    public String createItemForm(Model model) {
        model.addAttribute("itemForm", new ItemForm());
        return "items/additem";
    }

    @PostMapping("items/additem")
    public String createItem(ItemForm itemForm) {
        log.info("member id : " + itemForm.getMemberId());
        itemService.itemUpload(itemForm);
        return "redirect:/";
    }

    @GetMapping("{id}/items/list")
    public String createItemList(@PathVariable("id") String memberId, Model model) {
        Member member = memberService.findById(memberId);
        List<Item> items = itemService.findItems(member);
        model.addAttribute("items", items);
        return "items/list";
    }
}
