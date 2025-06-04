package daelim.book.rental.minyoung.user.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/account")
public class UserAccountController {

    private final UserAccountDao userAccountDao;

    public UserAccountController(UserAccountDao userAccountDao) {
        this.userAccountDao = userAccountDao;
    }

    @GetMapping("/createAccountForm")
    public String createAccountForm() {
        return "user/create_account_form";
    }

    @PostMapping("/createAccountConfirm")
    public String createAccountConfirm(UserAccountVo userAccountVo) {
        String nextPage = "user/create_account_ok";
        int result = userAccountDao.insertAccount(userAccountVo);
        if (result <= 0) {
            nextPage = "user/create_account_ng";
        }
        return nextPage;
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/login_form";
    }

    @PostMapping("/loginConfirm")
    public String loginConfirm(UserAccountVo userAccountVo, HttpServletResponse response, HttpSession session) {
        String nextPage = "user/login_ok";

        UserAccountVo loginedUser = userAccountDao.selectUser(userAccountVo);
        if (loginedUser == null) {
            nextPage = "user/login_ng";
        } else {
            Cookie cookie = new Cookie("loginUser", loginedUser.getId());
            cookie.setMaxAge(60 * 30);
            response.addCookie(cookie);
            session.setAttribute("loginedUser", loginedUser);
            session.setMaxInactiveInterval(60 * 30);
        }

        return nextPage;
    }

    @GetMapping("/logoutConfirm")
    public String logoutConfirm(@CookieValue(value = "loginUser", required = false) String loginUser,
                                HttpServletResponse response, HttpSession session) {
        String nextPage = "redirect:/user/account/loginForm";

        Cookie cookie = new Cookie("loginUser", loginUser);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        session.invalidate();

        return nextPage;
    }

    @GetMapping("/modifyAccountForm")
    public String modifyAccountForm(HttpSession session) {
        UserAccountVo loginedUser = (UserAccountVo) session.getAttribute("loginedUser");

        if (loginedUser == null) {
            return "redirect:/user/account/loginForm";
        }

        return "user/modify_account_form";
    }

    @PostMapping("/modifyAccountConfirm")
    public String modifyAccountConfirm(UserAccountVo userVo, HttpSession session) {
        String nextPage = "user/modify_account_ok";
        int result = userAccountDao.updateUserAccount(userVo);

        if (result > 0) {
            UserAccountVo updatedUser = userAccountDao.selectUserByNo(userVo.getNo());
            session.setAttribute("loginedUser", updatedUser);
            session.setMaxInactiveInterval(60 * 30);
        } else {
            nextPage = "user/modify_account_ng";
        }

        return nextPage;
    }

}
