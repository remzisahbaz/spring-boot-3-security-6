package example.controller;

import example.model.Blog;
import example.model.Status;
import example.service.BlogService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/haven")
public class HavenController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/")
    public String Haven(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("blogs", blogService.findByStatus(Status.ACTIEF));
        return "haven";
    }

    @PostMapping("/add")
    public String addBlog(@ModelAttribute("blog") Blog blog) {
        System.out.println(blog.getRedDate());
        blog.setContact("sampleContactTestingPurposesOnly");
        blog.setStatus(Status.ACTIEF); // TODO FIX MSS IETS MET INPUT
        if (blog.getUrl() != null && blog.getStatus() != null && blog.getContact() != null) {
            if (!blog.getUrl().isBlank() && !blog.getContact().isBlank()) {
                blogService.add(blog);
            }
        }
        return "redirect:/haven/";
    }

    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable("id") long id, Model model) {
        try {
            Blog blog = blogService.findById(id).orElseThrow(() -> new IllegalArgumentException("No blog with this id exists: " + id));
            blogService.delete(blog);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/haven/";
    }

    @PostMapping("/moveVerwijderd")
    public String moveToVerwijderd(long id) {
        blogService.updateStatus(id, Status.VERWIJDERD);
        return "redirect:/haven/";
    }

    @PostMapping("/havenAdd")
    public String addBlog(@ModelAttribute("blog") Blog blog, HttpSession session) {
        if (blog.getUrl() != null && blog.getStatus() != null && blog.getContact() != null) {
            if (!blog.getUrl().isBlank() && !blog.getContact().isBlank()) {
                Blog newBlog = new Blog(blog.getUrl(), blog.getContact(), blog.getStatus());

                return "redirect:/";
            }
        }
        return "havenAdd";
    }
}
