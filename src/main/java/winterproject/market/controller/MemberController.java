package winterproject.market.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import winterproject.market.Service.MemberService;
import winterproject.market.domain.Member;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("members/join")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/joinMember";
    }

    @PostMapping("members/join")
    public String createMember(MemberForm memberForm) {
        Member member = new Member(memberForm);
        memberService.join(member);
        return "redirect:/";
    }
}
