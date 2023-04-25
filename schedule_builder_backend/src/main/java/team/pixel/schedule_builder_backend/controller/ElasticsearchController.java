package team.pixel.schedule_builder_backend.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.pixel.schedule_builder_backend.dto.Course;
import team.pixel.schedule_builder_backend.dto.Event;
import team.pixel.schedule_builder_backend.service.CourseService;
import team.pixel.schedule_builder_backend.service.EventService;


@RestController
@RequestMapping("/api")
@Slf4j
public class ElasticsearchController {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private CourseService courseService;

    @Autowired
    private EventService eventService;


    @GetMapping("/course/search")
    public ResponseEntity<List<Course>> courseSearchByCode(@RequestParam String query) {
        return ResponseEntity.ok(courseService.getCourseByCourseCode(query));
    }


    @GetMapping("/course/all")
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/events/all")
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @PostMapping("/event/add")
    public ResponseEntity<String> eventAdd(@RequestBody Event event) {

        IndexRequest request = new IndexRequest("events");

        Map<String, Object> eventMap = new HashMap<>();
        eventMap.put("eventName", event.getEventName());
        eventMap.put("eventStartTime", event.getEventStartTime());
        eventMap.put("eventEndTime", event.getEventEndTime());
        eventMap.put("isRecurring", event.getIsRecurring());
        eventMap.put("eventLocation", event.getEventLocation());
        eventMap.put("eventDescription", event.getEventDescription());
        eventMap.put("isOutdoor", event.getIsOutdoor());

        request.source(eventMap);

        try {
            restHighLevelClient.index(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.info("Exception caught: " + e);
        }

        return ResponseEntity.ok("Done");

    }

    @PostMapping("/course/add")
    public ResponseEntity<String> courseAdd(@RequestBody Course course) {

        IndexRequest request = new IndexRequest("courses");

        Map<String, Object> courseMap = new HashMap<>();
        courseMap.put("courseName", course.getCourseName());
        courseMap.put("courseInstructor", course.getCourseInstructor());
        courseMap.put("courseVenue", course.getCourseVenue());
        courseMap.put("courseCode", course.getCourseCode());
        courseMap.put("isRecurring", course.getIsRecurring());
        courseMap.put("courseDescription", course.getCourseDescription());
        courseMap.put("courseStartTime", course.getCourseStartTime());
        courseMap.put("courseEndTime", course.getCourseEndTime());

        request.source(courseMap);

        try {
            restHighLevelClient.index(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.info("Exception caught: " + e);
        }

        return ResponseEntity.ok("Done");

    }


}
