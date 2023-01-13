package winterproject.market.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import winterproject.market.Repository.MemberRepository;
import winterproject.market.domain.Member;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;


    @Test
    void 회원_가입() {
        Member member = new Member();
        member.setId("wow");
        memberService.join(member);

        Member findMember = memberRepository.findOneById("wow");

        assertThat(member.getId()).isSameAs(findMember.getId());
    }

    @Test
    void 중복_회원_검사() {
        Member member = new Member();
        member.setId("1");
        memberService.join(member);

        Member dupMember = new Member();
        dupMember.setId("1");
        Assertions.assertThrows(IllegalStateException.class, () -> {
            memberService.join(dupMember);
        });
    }
}