package example.controller;

import example.model.Blog;
import example.model.Status;
import example.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/verwijderd")
public class VerwijderdHavenController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/")
    public String helaasNiet(Model model) {
        List<Object[]> verwijderdItems = blogService.findAllMergedWithMeningAndStatus(Status.VERWIJDERD);
        model.addAttribute("verwijderdItems", verwijderdItems);
        return "verwijderdHaven";
    }

    @PostMapping("/updateStatus")
    public String UpdateTeBekijkenStatus(Long id, String status) {
        blogService.updateStatus(id, Status.valueOf(status));
        return "redirect:/verwijderd/";
    }
    @PostMapping("/delete/{id}")
    public String deleteBlog(@PathVariable("id") long id, Model model) {
        try {
            Blog blog = blogService.findById(id).orElseThrow(() -> new IllegalArgumentException("No blog with this id exists: " + id));
            blogService.delete(blog);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/verwijderd/";
    }
}
