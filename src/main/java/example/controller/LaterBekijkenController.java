package example.controller;

import example.model.Status;
import example.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/later")
public class LaterBekijkenController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/")
    public String laterBekijken(Model model) {
        List<Object[]> laterBekijkenItems = blogService.findAllMergedWithMeningAndStatus(Status.WE_KIJKEN_LATER_NOG_EENS);
        model.addAttribute("laterBekijkenItems", laterBekijkenItems);
        return "laterBekijken";
    }

    @PostMapping("/updateStatus")
    public String UpdateTeBekijkenStatus(Long id, String status) {
        blogService.updateStatus(id, Status.valueOf(status));
        return "redirect:/later/";
    }
}
