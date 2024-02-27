package example.controller;

import example.model.LoginRequest;
import example.model.User;
import example.repository.UserRepository;
import example.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String viewLoginPage(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            model.addAttribute("loginRequest", new LoginRequest());
            return "login";
        } else {
             return "Index";
        }
    }


    @PostMapping("/login")
    public String processLogin(@ModelAttribute("loginRequest") LoginRequest loginRequest, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = userRepository.findByUsername(loginRequest.getUsername()).get();

        if (user != null && user.getPassword().equals(loginRequest.getPassword()) && "admin".equals(user.getRole())) {
            session.setAttribute("user", user);
            return "Index";
        } else {
            // Handle invalid credentials or username not found
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/";
        }
    }



    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.removeAttribute("user");
        redirectAttributes.addFlashAttribute("success", "You have successfully logged out.");
        return "redirect:login";
    }


}
