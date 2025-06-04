package daelim.book.rental.minyoung.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserHomeController {
    @GetMapping("")
    public String userHome() {
        return "user/user_home";
    }
}
