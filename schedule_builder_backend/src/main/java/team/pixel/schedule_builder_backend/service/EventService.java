package team.pixel.schedule_builder_backend.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import team.pixel.schedule_builder_backend.dto.Course;
import team.pixel.schedule_builder_backend.dto.Event;
import team.pixel.schedule_builder_backend.repository.EventRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;


    // Add a custom event
    public Event addEvent(Event event){
        Event savedEvent = eventRepository.save(event);
        return savedEvent;
    }

    //Returns all events part of the Events Index
    public List<Event> getAllEvents(){
        List<Event> eventList = StreamSupport.stream(eventRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return eventList;
    }

}
