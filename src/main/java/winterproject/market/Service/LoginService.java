package winterproject.market.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import winterproject.market.Repository.MemberRepository;
import winterproject.market.controller.LoginForm;
import winterproject.market.domain.Member;
import winterproject.market.exception.IdNotExistException;
import winterproject.market.exception.PasswordIncorrectException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {
    private final MemberRepository memberRepository;

    public AuthInfo login(LoginForm loginForm) throws IdNotExistException, PasswordIncorrectException {
        Member member = memberRepository.findOneById(loginForm.getId());
        validateLogin(member, loginForm);

        return new AuthInfo(member);
    }

    private static void validateLogin(Member member, LoginForm loginForm) {
        if (member == null)
            throw new IdNotExistException("존재 하지 않는 아이디입니다.");
        if (!member.getPassword().equals(loginForm.getPassword()))
            throw new PasswordIncorrectException("틀린 비밀번호 입니다. correct : " + member.getPassword()
             + "send : " + loginForm.getPassword());
    }
}
