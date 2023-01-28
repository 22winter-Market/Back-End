package winterproject.market.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {
    @GetMapping("additem") // url에 matching
    public String additem() {
        return "additem";
    }
}
