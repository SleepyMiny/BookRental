package daelim.book.rental.minyoung.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/account")
public class AdminAccountController {

    private final AdminAccountService adminAccountService;

    public AdminAccountController(AdminAccountService adminAccountService) {
        this.adminAccountService = adminAccountService;
    }

    @GetMapping("/createAccountForm")
    public String createAccountForm() {
        return "admin/account/create_account_form";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "admin/account/login_form";
    }

    @RequestMapping(value = "/createAccountConfirm", method = RequestMethod.POST)
    public String createAccountConfirm(AdminAccountVo adminAccountVo) {

        String nextPage = "admin/account/create_account_ok";
        int result = adminAccountService.createAccount(adminAccountVo);
        if (result <= 0) nextPage = "admin/account/create_account_ng";

        return nextPage;
    }

    @PostMapping("/loginConfirm")
    public String loginConfirm(AdminAccountVo adminAccountVo, HttpServletResponse response) {
        String nextPage = "admin/account/login_ok";

        AdminAccountVo loginedAdminAccountVo = adminAccountService.login(adminAccountVo);
        if (loginedAdminAccountVo == null) {
            nextPage = "admin/account/create_account_ng";
        } else {
            // cookie 설정
            Cookie cookie = new Cookie("loginMember", loginedAdminAccountVo.getId());
            cookie.setMaxAge(60 * 30);
            response.addCookie(cookie);
        }
        ;

        return nextPage;
    }

    @GetMapping("/logoutConfirm")
    public String logoutForm(@CookieValue(value = "loginMember", required = false) String loginMember, HttpServletResponse response) {
        System.out.println("[AdminMemberController.logoutForm]");
        String nextPage = "admin/account/login_form";
        Cookie cookie = new Cookie("loginMember", loginMember);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return nextPage;
    }

    @GetMapping("/modifyAccountForm")
    public String modifyAccountForm(HttpSession session) {
        System.out.println("[AdminMemberController.modifyAccountForm]");
        String nextPage = "/admin/account/modify_account_form";
        AdminAccountVo adminMemberVo = (AdminAccountVo) session.getAttribute("loginedAdminAccountVo");
        if(adminMemberVo == null) {
            nextPage = "redirect:/admin/account/loginForm";
        }
        return nextPage;
    }

    @PostMapping("/modifyAccountConfrim")
    public String modifyAccountConfirm(AdminAccountVo adminAccountVo, HttpSession session) {
        String nextPage = "admin/account/modify_account_ok";
        int result = adminAccountService.modifyAccount(adminAccountVo);
        if (result > 0) {
            AdminAccountVo loginedAdminAccountVo = adminAccountService.getLoginedAdminAccountVo(adminAccountVo.getNo());
            session.setAttribute("loginedAdminAccountVo", loginedAdminAccountVo);
            session.setMaxInactiveInterval(60 * 30);
        } else {
            nextPage = "admin/account/modify_account_ng";
        }
        return nextPage;
    }

    @GetMapping("/findPasswordForm")
    public String findPasswordForm() {
        String nextPage = "admin/account/find_password_form";
        return nextPage;
    }
}
