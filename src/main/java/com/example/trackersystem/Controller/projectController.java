package com.example.trackersystem.Controller;

import com.example.trackersystem.API.ApiResponse;
import com.example.trackersystem.Model.projectModel;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/project")
public class projectController {

    ArrayList<projectModel> projects = new ArrayList<>();
    @PostMapping("/add")
    public ApiResponse addProject(@RequestBody projectModel project) {
        projects.add(project);
        return new ApiResponse("Successfully added project");
    }

    @GetMapping("/get")
    public ArrayList <projectModel> getProject() {
        return projects;
    }

    @PostMapping("/update/{index}")
    public ApiResponse updateProject(@PathVariable int index, @RequestBody projectModel project) {
        projects.set(index, project);
        return new ApiResponse("Successfully updated project");
    }
    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteProject(@PathVariable int index) {
        projects.remove(index);
        return new ApiResponse("Successfully deleted project");
    }
//not working
    @PutMapping("/updates")
    public String setstatus(@RequestBody String status) {
        if (Objects.equals(status, "done")) {
            return "Project not done";
        } else if (Objects.equals(status, "not done")) {
            return "Project done";
        }
        return status;
    }

    

    @GetMapping("/searchtitle")
    public ArrayList<projectModel> searchProjectByTitle(@RequestParam String title) {
        ArrayList<projectModel> foundProjects = new ArrayList<>();
        for (projectModel proj : projects) {
            if (proj.getTitle().equalsIgnoreCase(title)) {
                foundProjects.add(proj);
            }
        }
        return foundProjects;
    }

    @GetMapping("/getByCompany")
    public ArrayList<projectModel> getProjectsByCompany(@RequestParam String companyName) {
        ArrayList<projectModel> projByCompany = new ArrayList<>();
        for (projectModel proj : projects) {
            if (proj.getCompanyName().equalsIgnoreCase(companyName)) {
                projByCompany.add(proj);
            }
        }
        return projByCompany;
    }



}
