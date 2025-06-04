package daelim.book.rental.minyoung.user.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserAccountController {

    @GetMapping("/user")
    public String userHome() {
        return "admin/user/user_home";
    }
}
