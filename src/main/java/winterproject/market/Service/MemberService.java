package winterproject.market.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import winterproject.market.Repository.MemberRepository;
import winterproject.market.domain.Member;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public String join(Member member) {
        validateDuplicate(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicate(Member member) {
        List<Member> members = memberRepository.findById(member.getId());
        if (!members.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public String findById(Member member) {
        List<Member> findMembers = memberRepository.findById(member.getId());
        return findMembers.get(0).getId();
    }
}
