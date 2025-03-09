package com.example.demo.Controller;
import com.example.demo.apiResponse.*;
import com.example.demo.Model.studentModel;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class studentController {
    ArrayList<studentModel> students = new ArrayList<>();

    @PostMapping("/add")
    public ApiResponse addStudent(@RequestBody studentModel student) {
        students.add(student);
        return new ApiResponse("student added successfully");
    }
    @GetMapping("/get")
    public ArrayList<studentModel> getStudents() {
        return students;
    }
    @PostMapping("/update/{index}")
    public ApiResponse setStudent(@PathVariable int index, @RequestBody studentModel student) {
        students.set(index, student);
        return new ApiResponse("student updated successfully");
    }
    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteStudent(@PathVariable int index) {
        students.remove(index);
        return new ApiResponse("student deleted successfully");
    }

    //TODO: didnt work it gives 404 error
    @GetMapping("/gethonor")
    public ApiResponse getStudent(@PathVariable int index) {
        for (studentModel ignored : students) {
            if (students.get(index).getGBA()==4.75) {
                return new ApiResponse("First class honor");
            } else if (students.get(index).getGBA()==4.5) {
                return new ApiResponse("Second class honor");
            }else new ApiResponse("You are not a class honor");
        }return null;
    }

    @GetMapping("/getgrater/{index}")
    public ArrayList<studentModel> getStudentGrater(@PathVariable int index) {
        double average = students.get(index).getGBA();
        for (studentModel student : students) {
            if (student.getGBA()> average) {
                return getStudents();
            }
        }return null;
    }
    @GetMapping("/getgraterav")
    public List<studentModel> getStudentsAboveAverage() {
        double totalGPA = 0.0;
        for (studentModel student : students) {
            totalGPA += student.getGBA();
        }
        double averageGPA = students.size() > 0 ? totalGPA / students.size() : 0.0;

        List<studentModel> lowerSavrage = new ArrayList<>();
        for (studentModel student : students) {
            if (student.getGBA() > averageGPA) {
                lowerSavrage.add(student);
            }
        }
        return lowerSavrage;
    }




}
