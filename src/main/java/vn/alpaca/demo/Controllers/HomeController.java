package vn.alpaca.demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class HomeController {
    @GetMapping("/home")
    public String viewHome(){
        return "home";
    }

    @GetMapping("/login")
    public String viewLogin(){
        return "login";
    }
}
