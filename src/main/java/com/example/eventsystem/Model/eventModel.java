package com.example.eventsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public class eventModel {

    String id;
    String discription;
    int capacity;
    @JsonFormat(pattern = "yyyy-MM-DD HH:MM:SS")
    LocalDateTime startDate;
    @JsonFormat(pattern = "yyyy-MM-DD HH:MM:SS")
    LocalDateTime endDate;
}
