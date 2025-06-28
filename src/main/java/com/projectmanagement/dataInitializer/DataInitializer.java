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

        // Get all enum values for diversification
        ProjectStatus[] projectStatuses = ProjectStatus.values();
        ActivityStatus[] activityStatuses = ActivityStatus.values();
        Priorite[] priorites = Priorite.values();

        List<Project> projects = new ArrayList<>();
        for (int i = 1; i <= 15; i++) { // Increased number of projects for more diversification
            Project project = new Project();
            project.setName("Project " + i);
            project.setDescription("Description of project " + i);
            project.setStartDate(LocalDate.now().minusDays(i * 5)); // Diversify start dates
            project.setEndDate(LocalDate.now().plusMonths(6).plusDays(i * 10)); // Diversify end dates
            project.setTotalBudget(new BigDecimal("10000").multiply(BigDecimal.valueOf(i)).add(new BigDecimal("5000")));
            // Assign diverse ProjectStatus
            project.setStatus(projectStatuses[(i - 1) % projectStatuses.length]);
            project.setResponsibles(responsibles.subList(0, i % responsibles.size()));
            projects.add(projectRepository.save(project));
        }

        List<Activity> activities = new ArrayList<>();
        for (int i = 1; i <= 30; i++) { // Increased number of activities for more diversification
            Activity act = new Activity();
            act.setTitle("Activity " + i);
            act.setDescription("Description of activity " + i);
            act.setPlannedStartDate(LocalDate.now().minusDays(i * 2)); // Diversify start dates
            act.setPlannedEndDate(LocalDate.now().plusWeeks(i)); // Diversify end dates
            act.setActualStartDate(LocalDate.now().minusDays(i * 2 - 5)); // Diversify actual start dates
            // Assign diverse ActivityStatus and Priorite
            act.setStatus(activityStatuses[(i - 1) % activityStatuses.length]);
            act.setPriorite(priorites[(i - 1) % priorites.length]);
            act.setProject(projects.get((i - 1) % projects.size())); // Assign to diverse projects
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

        for (int i = 0; i < activities.size(); i++) { // Ensure Usage matches created activities
            Usage usage = new Usage();
            usage.setActivity(activities.get(i));
            usage.setResource(resources.get(i % resources.size())); // Cycle through resources
            usageRepository.save(usage);
        }

        for (int i = 1; i <= activities.size(); i++) { // Ensure Document matches created activities
            Document doc = new Document();
            doc.setName("Document" + i + ".pdf");
            doc.setType(DocumentType.CONTRACT);
            doc.setQuantity(i);
            doc.setFilePath("sample/path/doc" + i + ".pdf");
            doc.setContentType("application/pdf");
            doc.setSize(10000L * i);
            doc.setOriginalFileName("original-doc" + i + ".pdf");
            doc.setActivity(activities.get(i - 1));
            documentRepository.save(doc);
        }
    }
}
