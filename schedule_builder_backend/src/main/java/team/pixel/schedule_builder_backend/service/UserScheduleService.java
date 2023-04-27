package team.pixel.schedule_builder_backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.pixel.schedule_builder_backend.dto.DailyEventDetails;
import team.pixel.schedule_builder_backend.dto.UserSchedule;
import team.pixel.schedule_builder_backend.repository.UserScheduleRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserScheduleService {

    @Autowired
    UserScheduleRepository userScheduleRepository;

    public UserSchedule getUserScheduleByEmail(String emailId) {
        List<UserSchedule> userScheduleList = userScheduleRepository.findByUserEmail(emailId);
        return userScheduleList.get(0);
    }


    public void deleteUserScheduleIfExists(String emailId) {
        List<UserSchedule> userScheduleList = userScheduleRepository.findByUserEmail(emailId);
        if (!userScheduleList.isEmpty()) {
            userScheduleRepository.deleteAll(userScheduleList);
        }
    }


//    public DailyEventDetails getUserDailySchedule(String emailId, String currentDate){
//    }
}
