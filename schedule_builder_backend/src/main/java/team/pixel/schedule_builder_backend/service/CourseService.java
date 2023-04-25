package team.pixel.schedule_builder_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import team.pixel.schedule_builder_backend.dto.Course;
import team.pixel.schedule_builder_backend.repository.CourseRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    //Find the course by courseCode
    public List<Course> getCourseByCourseCode(String courseCode) {
        return  courseRepository.findByCourseCode(courseCode);

    }



    //Returns all courses part of the Courses Index
    public List<Course> getAllCourses(){
        List<Course> courseList = StreamSupport.stream(courseRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return courseList;
    }
}
