package daelim.book.rental.minyoung.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminHomeController {

    @GetMapping("/home")
    public String home(){
        System.out.println(">>>> home 컨트롤러 호출됨");
        return "admin/home";
    }
}
