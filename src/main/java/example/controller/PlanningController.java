package example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import example.model.Vangst;
import example.service.PlanningService;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping("/planning")
public class PlanningController {

    @Autowired
    private PlanningService planningService;

    @GetMapping("/")
    public String Planning(Model model) {
        List<Vangst> WeekPlanning = planningService.getVangsten();
        model.addAttribute("weekPlanning", WeekPlanning);
        // print a list of vangsten in console
        for (Vangst vangst : WeekPlanning) {
            System.out.println(vangst.getDatum());
            // if redactor is not null, print getRedactor().getId() and getRedactor().getName()
            if (vangst.getRedactor() != null) {
                System.out.println(vangst.getRedactor().getId());
                System.out.println(vangst.getRedactor().getName());
            }
        }
        return "planning";
    }
}
