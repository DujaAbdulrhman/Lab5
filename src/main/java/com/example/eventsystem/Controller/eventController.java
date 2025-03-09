/*
package com.example.eventsystem.Controller;

import com.example.eventsystem.Api.ApiResponse;
import jdk.jfr.Event;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/api/v1/event")
public class eventController {
    ArrayList<Event> events = new ArrayList<>();

    @PostMapping("/add")
    public ApiResponse addEvent(@RequestBody Event event) {
        events.add(event);
        return new ApiResponse("Event added successfully");
    }

    @GetMapping("/get")
    public ArrayList<Event> getEvent() {
        return events;
    }

}
*/


package com.example.eventsystem.Controller;

import com.example.eventsystem.Api.ApiResponse;
import com.example.eventsystem.Model.eventModel;  // Ensure this import points to your Event model
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/event")
public class eventController {
    private final ArrayList<eventModel> events = new ArrayList<>();

    @PostMapping("/add")
    public ApiResponse addEvent(@RequestBody eventModel event) {
        events.add(event);
        return new ApiResponse("Event added successfully");
    }

    @GetMapping("/get")
    public ArrayList<eventModel> getEvent() {
        return events;
    }

    @PutMapping("/update/{id}")
    public ApiResponse updateEvent(@PathVariable Long id, @RequestBody eventModel updatedEvent) {
        Optional<eventModel> eventOptional = events.stream().filter(event -> event.getId().equals(id)).findFirst();
        if (eventOptional.isPresent()) {
            eventModel event = eventOptional.get();
            event.setDiscription(updatedEvent.getDiscription());
            event.setCapacity(updatedEvent.getCapacity());
            event.setStartDate(updatedEvent.getStartDate());
            event.setEndDate(updatedEvent.getEndDate());
            return new ApiResponse("Event updated successfully");
        } else {
            return new ApiResponse("Event not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteEvent(@PathVariable Long id) {
        events.removeIf(event -> event.getId().equals(id));
        return new ApiResponse("Event deleted successfully");
    }

    @PutMapping("/changecap/{id}")
    public ApiResponse changeCapacity(@PathVariable Long id, @RequestParam int capacity) {
        Optional<eventModel> eventOptional = events.stream().filter(event -> event.getId().equals(id)).findFirst();
        if (eventOptional.isPresent()) {
            eventOptional.get().setCapacity(capacity);
            return new ApiResponse("Event capacity updated successfully");
        } else {
            return new ApiResponse("Event not found");
        }
    }

    @GetMapping("/search/{id}")
    public eventModel searchEventById(@PathVariable Long id) {
        return events.stream().filter(event -> event.getId().equals(id)).findFirst().orElse(null);
    }
}
