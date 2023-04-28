package team.pixel.schedule_builder_backend.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.annotation.Nullable;

@Document(indexName = "events")
@Data
public class Event {
    @Id
    @Nullable
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Text)
    private String eventName;

    @Field(type = FieldType.Text)
    private String eventStartTime;

    @Field(type = FieldType.Text)
    private String eventEndTime;

    @Field(type = FieldType.Boolean)
    private Boolean isRecurring;

    @Field(type = FieldType.Text)
    private String eventLocation;

    @Field(type = FieldType.Text)
    private String eventDescription;

    @Field(type = FieldType.Boolean)
    private Boolean isOutdoor;

}

