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
import team.pixel.schedule_builder_backend.dto.UserSchedule;
import team.pixel.schedule_builder_backend.service.CourseService;
import team.pixel.schedule_builder_backend.service.EventService;
import team.pixel.schedule_builder_backend.service.UserScheduleService;


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

    @Autowired
    private UserScheduleService userScheduleService;


    @GetMapping("/course/search")
    public ResponseEntity<List<Course>> courseSearchByCode(@RequestParam String query) {
        return ResponseEntity.ok(courseService.getCourseByCourseCode(query));
    }

    @GetMapping("/course/codes")
    public ResponseEntity<List<String>> getCourseCodes() {
        return ResponseEntity.ok(courseService.fetchAllCourseCodes());
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


    @PostMapping("/userschedule")
    public ResponseEntity<String> userScheduleAdd(@RequestBody UserSchedule userSchedule) {
        IndexRequest request = new IndexRequest("userschedule");

        //Delete previous User schedule for the email, if exists
        userScheduleService.deleteUserScheduleIfExists(userSchedule.getUserEmail());

        Map<String, Object> userScheduleMap = new HashMap<>();

        Map<String, String> parttimeScheduleMap = new HashMap<>();
        Map<String, String> personalAppointmentMap = new HashMap<>();

        userSchedule.getPartTimeSchedule().forEach( (key ,value)-> {
            parttimeScheduleMap.put(key,value);
        });

        userSchedule.getPersonalAppointment().forEach( (key ,value)-> {
            personalAppointmentMap.put(key,value);
        });

        userScheduleMap.put("coursesEnrolled", userSchedule.getCoursesEnrolled());
        userScheduleMap.put("major", userSchedule.getMajor());
        userScheduleMap.put("partTime", userSchedule.getPartTime());
        userScheduleMap.put("partTimeLoc",userSchedule.getPartTimeLoc());
        userScheduleMap.put("partTimeSchedule",  parttimeScheduleMap);
        userScheduleMap.put("personalAppointment", personalAppointmentMap);
        userScheduleMap.put("userEmail", userSchedule.getUserEmail());
        userScheduleMap.put("yearOfStudy",userSchedule.getYearOfStudy());

        request.source(userScheduleMap);

        try {
            restHighLevelClient.index(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.info("Exception caught: " + e);
        }

        return ResponseEntity.ok("Done");
    }

    @GetMapping("/userschedule")
    public ResponseEntity<UserSchedule> getUserSchedule(@RequestParam String emailId) {
        return ResponseEntity.ok(userScheduleService.getUserScheduleByEmail(emailId));
    }


    //TBD
//    @GetMapping("/dailyschedule")
//    public ResponseEntity<List<UserSchedule>> getUserSchedule(@RequestParam String emailId, @RequestParam String currentDate) {
//        return ResponseEntity.ok(userScheduleService.getUserDailySchedule(emailId,currentDate));
//    }
}
