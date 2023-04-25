package team.pixel.schedule_builder_backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.pixel.schedule_builder_backend.dto.Event;
import team.pixel.schedule_builder_backend.dto.UserSchedule;
import team.pixel.schedule_builder_backend.repository.UserScheduleRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserScheduleService {

    @Autowired
    UserScheduleRepository userScheduleRepository;

    public List<UserSchedule> getUserScheduleByEmail(String emailId){
        List<UserSchedule> userSchedule = userScheduleRepository.findByUserEmail(emailId);
        return userSchedule;
    }
}
