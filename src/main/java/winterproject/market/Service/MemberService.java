package winterproject.market.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import winterproject.market.Repository.MemberRepository;
import winterproject.market.domain.Member;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public String join(Member member, BindingResult bindingResult) {
        try {
            validateDuplicate(member, bindingResult);
            memberRepository.save(member);
        } catch (IllegalStateException e) {
            return null;
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

    public String findById(Member member) {
        List<Member> findMembers = memberRepository.findById(member.getId());
        return findMembers.get(0).getId();
    }

    public int overlappedID(String id) {
        List<Member> findById = memberRepository.findById(id);
        if (findById.isEmpty())
            return 0;
        return 1;
    }
}
