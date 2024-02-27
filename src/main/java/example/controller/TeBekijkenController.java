package example.controller;

import example.model.Blog;
import example.model.Status;
import example.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/teBekijken")
public class TeBekijkenController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/")
    public String teBekijken(Model model) {

        List<Blog> teBekijkenItems = blogService.findByStatus(Status.TE_BEKIJKEN);

        model.addAttribute("havenSuggestion", new Blog());
        model.addAttribute("teBekijkenItems", teBekijkenItems);
        // debug what is in TeBekijkenItems
         System.out.println(teBekijkenItems.stream().map(Object::toString).collect(Collectors.joining(", ")));
        return "teBekijken";
    }

    @PostMapping("/suggest")
    public String suggestBlog(@ModelAttribute("havenSuggestion") Blog blog) {
        blog.setStatus(Status.TE_BEKIJKEN);
        blog.setContact("sampleContactTestingPurposesOnly");
        blogService.add(blog);
        return "redirect:/teBekijken/";
    }


    // update status of blog to "te bekijken"
    @PostMapping("/updateStatus")
    public String UpdateTeBekijkenStatus(Long id, String status) {
        blogService.updateStatus(id, Status.valueOf(status));
        return "redirect:/teBekijken/";
    }
}
