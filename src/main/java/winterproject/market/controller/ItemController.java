package winterproject.market.controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import winterproject.market.Service.ItemService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;

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
}
