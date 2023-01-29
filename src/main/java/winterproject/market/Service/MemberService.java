package winterproject.market.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import winterproject.market.Repository.MemberRepository;
import winterproject.market.controller.MemberForm;
import winterproject.market.domain.Member;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;

    public String join(Member member, BindingResult bindingResult) throws IllegalStateException{
        try {
            validateDuplicate(member, bindingResult);
            memberRepository.save(member);
        } catch (IllegalStateException e) {
            throw e;
        }
        return member.getId();
    }

    private void validateDuplicate(Member member, BindingResult bindingResult) {
        List<Member> members = memberRepository.findById(member.getId());
        if (!members.isEmpty()) {
            bindingResult.addError(new FieldError("id", "id", "중복된 id 입니다."));
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public Member findById(String id) {
        Member member = memberRepository.findOneById(id);
        return member;
    }

    public int overlappedID(String id) {
        List<Member> findById = memberRepository.findById(id);
        if (findById.isEmpty())
            return 0;
        return 1;
    }

    public void updateProfile(MemberForm memberForm, BindingResult bindingResult) {
        Member member = memberRepository.findOneById(memberForm.getId());
        member.setEmail(memberForm.getEmail());
        member.setPassword(memberForm.getPassword());
        member.setNickname(memberForm.getNickname());
    }
}
