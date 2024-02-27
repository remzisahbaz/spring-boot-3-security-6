package example.controller;

import example.model.Status;
import example.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/helaas")
public class HelaasNietController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/")
    public String helaasNiet(Model model) {
        List<Object[]> helaasNietItems = blogService.findAllMergedWithMeningAndStatus(Status.HELAAS_NIET);
        model.addAttribute("helaasNietItems", helaasNietItems);
        return "helaasNiet";
    }

    @PostMapping("/updateStatus")
    public String UpdateHelaasNietStatus(Long id, String status) {
        blogService.updateStatus(id, Status.valueOf(status));
        return "redirect:/helaas/";
    }
}
