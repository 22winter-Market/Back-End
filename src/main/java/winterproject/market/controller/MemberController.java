package winterproject.market.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import winterproject.market.Service.MemberService;
import winterproject.market.domain.Member;

import javax.validation.Valid;

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
    public String createMember(@Valid MemberForm memberForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "members/joinMember";
        }
        try {
            Member member = new Member(memberForm, bindingResult);
            memberService.join(member, bindingResult);
        } catch (IllegalStateException e) {
            if (bindingResult.hasErrors()) {
                return "members/joinMember";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/members/{id}")
    public String memberUpdateForm(@PathVariable("id") String memberId, Model model) {
        Member member = memberService.findById(memberId);
        MemberForm memberForm = new MemberForm(member);
        model.addAttribute("memberForm", memberForm);
        return "members/update";
    }

    @PostMapping("/members/{id}")
    public String memberUpdate(MemberForm memberForm, BindingResult bindingResult) {
        try {
            memberService.updateProfile(memberForm, bindingResult);
        } catch (IllegalStateException e) {
            if (bindingResult.hasErrors()) {
                return "members/update";
            }
        }
        return "redirect:/";
    }
}
