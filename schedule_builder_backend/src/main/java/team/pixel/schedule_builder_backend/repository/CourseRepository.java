package team.pixel.schedule_builder_backend.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import team.pixel.schedule_builder_backend.dto.Course;

import java.util.List;

@Repository
public interface CourseRepository extends ElasticsearchRepository<Course,String> {
    List<Course> findByCourseCode(String courseCode);

}
