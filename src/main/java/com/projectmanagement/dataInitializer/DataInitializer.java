package com.projectmanagement.dataInitializer;

// This file is responsible for initializing dummy data in the database when the
// Spring Boot application starts. It implements CommandLineRunner, which means
// its 'run' method will be executed automatically after the application context
// is loaded.
//
// The purpose of DataInitializer is to:
// 1. Populate Database: Provide initial data for development and testing purposes,
//    so you don't start with an empty database.
// 2. Demonstrate Relationships: Create interconnected data across different entities
//    (e.g., documents associated with activities, activities with projects).
// 3. Ease of Development: Allows developers to quickly get a working dataset
//    without manual data entry.
//
// For Document initialization specifically:
// - It creates sample Document entities.
// - It sets the 'name', 'type', 'quantity', 'fileSize', 'fileType', 'file' (dummy byte data),
//   'originalFileName', and associates each document with an existing 'Activity'.
// - The 'file' field is populated with simple dummy byte data for demonstration.

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
            Responsible resp = new Responsible();
            resp.setEmail("admin" + i + "@example.com");
            resp.setFirstname("Firstname" + i);
            resp.setLastname("Lastname" + i);
            resp.setPassword("password");
            resp.setRole(i % 2 == 0 ? Role.ADMIN : Role.VISITOR);
            resp.setPhone("77" + i + "1234567");
            resp.setProjects(new ArrayList<>());
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

        // for (int i = 1; i <= activities.size(); i++) {
        //     Document doc = new Document();
        //     doc.setName("Document" + i + ".pdf");
        //     doc.setType(DocumentType.CONTRACT);
        //     doc.setQuantity(i);
        //     doc.setFileSize(10000L * i); // Dummy file size
        //     doc.setFileType("application/pdf"); // Dummy file type
        //     doc.setFile(("This is dummy content for document " + i).getBytes()); // Dummy data
        //     doc.setOriginalFileName("original-doc" + i + ".pdf");
        //     doc.setActivity(activities.get(i - 1));
        //     documentRepository.save(doc);
        // }
    }
}
