package example.service;

import example.model.Vangst;
import example.repository.VangstRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlanningService {

    @Autowired
    private VangstRepository vangstRepository;


    public List<LocalDate> getWeekPlanning() {
        return generateWeekPlanning();
    }

    // get vangsten from VangstRepository and filter them with de WeekPlanning
    // return a list of vangsten
    public List<Vangst> getVangsten() {
        List<Vangst> vangsten = new ArrayList<>();
        List<LocalDate> weekPlanning = generateWeekPlanning();
        for (Vangst vangst : vangstRepository.findAll()) {
            System.out.println(vangst.getDatum());
            if (weekPlanning.contains(vangst.getDatum())) {
                vangsten.add(vangst);
            }
        }
        return vangsten;
    }


    private List<LocalDate> generateWeekPlanning() {
        List<LocalDate> weekPlanningList = new ArrayList<>();

        // Huidige datum
        LocalDate today = LocalDate.now();

        // Bereken vijf weken in het verleden en vijf weken in de toekomst
        for (int i = -5; i <= 5; i++) {
            LocalDate weekStartDate = today.plusWeeks(i).with(DayOfWeek.FRIDAY);
            System.out.println(weekStartDate);

            // Voeg toewijzing toe aan de lijst
            weekPlanningList.add(weekStartDate);
        }

        return weekPlanningList;
    }
}