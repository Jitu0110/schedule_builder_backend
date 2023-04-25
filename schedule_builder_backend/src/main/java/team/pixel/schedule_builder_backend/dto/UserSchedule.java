package team.pixel.schedule_builder_backend.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Dynamic;


import java.util.Map;


@Document(indexName = "userschedule")
@Data
public class UserSchedule {
    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String[] coursesEnrolled;

    @Field(type = FieldType.Text)
    private String major;

    @Field(type = FieldType.Keyword)
    private String partTime;

    @Field(type = FieldType.Text)
    private String partTimeLoc;

    @Field(type = FieldType.Object)
    private Map<String, String> partTimeSchedule;

    @Field(type = FieldType.Object)
    private Map<String, String> personalAppointment;

    @Field(type = FieldType.Keyword)
    private String userEmail;

    @Field(type = FieldType.Keyword)
    private String yearOfStudy;

}
