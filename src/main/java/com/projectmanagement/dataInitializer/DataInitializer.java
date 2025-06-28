package com.projectmanagement.dataInitializer;
import com.projectmanagement.entity.*;
import com.projectmanagement.enums.*;
import com.projectmanagement.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final ProjectRepository projectRepository;
    private final ActivityRepository activityRepository;
    private final ResponsibleRepository responsibleRepository;
    private final IndicatorRepository indicatorRepository;
    private final ResourceRepository resourceRepository;
    private final UsageRepository usageRepository;
    private final DocumentRepository documentRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Responsible> responsibles = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Responsible resp = new Responsible("admin" + i + "@example.com", "Firstname" + i, "Lastname" + i,
                    "password", i % 2 == 0 ? Role.ADMIN : Role.VISITOR, "77" + i + "1234567", new ArrayList<>());
            responsibles.add(responsibleRepository.save(resp));
        }

        ProjectStatus[] projectStatuses = ProjectStatus.values();
        ActivityStatus[] activityStatuses = ActivityStatus.values();
        Priorite[] priorites = Priorite.values();

        List<Project> projects = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            Project project = new Project();
            project.setName("Project " + i);
            project.setDescription("Description of project " + i);
            project.setStartDate(LocalDate.now().minusDays(i * 5));
            project.setEndDate(LocalDate.now().plusMonths(6).plusDays(i * 10));
            project.setTotalBudget(new BigDecimal("10000").multiply(BigDecimal.valueOf(i)).add(new BigDecimal("5000")));
            project.setStatus(projectStatuses[(i - 1) % projectStatuses.length]);
            project.setResponsibles(responsibles.subList(0, i % responsibles.size()));
            projects.add(projectRepository.save(project));
        }

        List<Activity> activities = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            Activity act = new Activity();
            act.setTitle("Activity " + i);
            act.setDescription("Description of activity " + i);
            act.setPlannedStartDate(LocalDate.now().minusDays(i * 2));
            act.setPlannedEndDate(LocalDate.now().plusWeeks(i));
            act.setActualStartDate(LocalDate.now().minusDays(i * 2 - 5));
            act.setStatus(activityStatuses[(i - 1) % activityStatuses.length]);
            act.setPriorite(priorites[(i - 1) % priorites.length]);
            act.setProject(projects.get((i - 1) % projects.size()));
            activities.add(activityRepository.save(act));
        }

        for (int i = 1; i <= 5; i++) {
            Indicator ind = new Indicator();
            ind.setName("Indicator " + i);
            ind.setDescription("Indicator desc " + i);
            ind.setUnit("unit");
            ind.setTargetValue(1000.0 * i);
            ind.setActualValue(100.0 * i);
            ind.setDate(java.sql.Date.valueOf(LocalDate.now()));
            ind.setProject(projects.get(i - 1));
            indicatorRepository.save(ind);
        }

        List<Resource> resources = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Resource rsc = new Resource();
            rsc.setName("Resource " + i);
            rsc.setType(ResourceType.INPUT);
            rsc.setQuantity(100.0 * i);
            rsc.setUnit("kg");
            resources.add(resourceRepository.save(rsc));
        }

        for (int i = 0; i < activities.size(); i++) {
            Usage usage = new Usage();
            usage.setActivity(activities.get(i));
            usage.setResource(resources.get(i % resources.size()));
            usageRepository.save(usage);
        }
    }
}
