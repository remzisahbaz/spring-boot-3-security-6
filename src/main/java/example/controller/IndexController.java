package example.controller;

import example.model.LoginRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            model.addAttribute("loginRequest", new LoginRequest());
            return "Login";
        } else {
            return "Index";
        }
    }
}