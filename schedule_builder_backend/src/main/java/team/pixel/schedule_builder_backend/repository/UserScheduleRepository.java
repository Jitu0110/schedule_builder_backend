package team.pixel.schedule_builder_backend.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import team.pixel.schedule_builder_backend.dto.Course;
import team.pixel.schedule_builder_backend.dto.UserSchedule;

import java.util.List;


@Repository
public interface UserScheduleRepository extends ElasticsearchRepository<UserSchedule,String> {

    List<UserSchedule> findByUserEmail(String emailId);
}
