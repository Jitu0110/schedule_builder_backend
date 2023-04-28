package team.pixel.schedule_builder_backend.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import team.pixel.schedule_builder_backend.dto.Event;


@Repository
public interface EventRepository extends ElasticsearchRepository<Event,String> {
}
