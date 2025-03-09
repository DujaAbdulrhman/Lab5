package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class studentModel {
    private String id;
    private String name;
    private int age;
    private String degree;
    private double GBA;
}
