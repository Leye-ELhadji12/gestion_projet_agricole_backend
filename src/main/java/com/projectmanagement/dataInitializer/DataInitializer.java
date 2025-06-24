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

        List<Project> projects = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Project project = new Project();
            project.setName("Project " + i);
            project.setDescription("Description of project " + i);
            project.setStartDate(LocalDate.now());
            project.setEndDate(LocalDate.now().plusMonths(6));
            project.setTotalBudget(new BigDecimal("10000").multiply(BigDecimal.valueOf(i)));
            project.setStatus(ProjectStatus.EN_COURS);
            project.setResponsibles(responsibles.subList(0, i % 5));
            projects.add(projectRepository.save(project));
        }

        List<Activity> activities = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Activity act = new Activity();
            act.setTitle("Activity " + i);
            act.setDescription("Description of activity " + i);
            act.setPlannedStartDate(LocalDate.now());
            act.setPlannedEndDate(LocalDate.now().plusWeeks(i));
            act.setActualStartDate(LocalDate.now());
            act.setStatus(ActivityStatus.IN_PROGRESS);
            act.setProject(projects.get(i - 1));
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

        for (int i = 0; i < 5; i++) {
            Usage usage = new Usage();
            usage.setActivity(activities.get(i));
            usage.setResource(resources.get(i));
            usageRepository.save(usage);
        }

        for (int i = 1; i <= 5; i++) {
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
