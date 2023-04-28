package team.pixel.schedule_builder_backend.dto;


import lombok.Data;

@Data
public class DailyEventDetails {

    public String eventName;

    public Boolean isCompleted = false;

    public String eventTime;

}
