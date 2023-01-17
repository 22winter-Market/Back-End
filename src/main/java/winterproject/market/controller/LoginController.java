package winterproject.market.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import winterproject.market.Service.AuthInfo;
import winterproject.market.Service.LoginService;
import winterproject.market.exception.IdNotExistException;
import winterproject.market.exception.PasswordIncorrectException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("members/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "members/login";
    }

    @PostMapping("members/login")
    public String loginSuccess(@Valid LoginForm loginForm, BindingResult bindingResult, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "members/login";
        }
        try {
            AuthInfo authInfo = loginService.login(loginForm);
            httpSession.setAttribute("authInfo", authInfo);
        } catch (IdNotExistException e) {
            bindingResult.rejectValue("error", "not exist", "login failed!");
        } catch (PasswordIncorrectException e) {
            bindingResult.rejectValue("error", "incorrect", "incorrect password!");
        } finally {
            if (bindingResult.hasErrors()) {
                return "members/login";
            }
        }
        return "redirect:/";
    }

    @GetMapping("members/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }
}
